package com.canteen.canteen_backend.security;

import com.canteen.canteen_backend.entity.Admin;
import com.canteen.canteen_backend.entity.Customer;
import com.canteen.canteen_backend.entity.Staff;
import com.canteen.canteen_backend.repository.AdminRepository;
import com.canteen.canteen_backend.repository.CustomerRepository;
import com.canteen.canteen_backend.repository.StaffRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    public JwtFilter(JwtUtil jwtUtil,
                     AdminRepository adminRepository,
                     CustomerRepository customerRepository,
                     StaffRepository staffRepository) {
        this.jwtUtil = jwtUtil;
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);

                CustomUserDetails userDetails = null;

                // 🔐 Load user from DB based on role
                if ("ADMIN".equals(role)) {
                    Admin admin = adminRepository.findByEmail(email)
                            .orElseThrow();
                    userDetails = new CustomUserDetails(
                            admin.getEmail(),
                            admin.getPassword(),
                            "ADMIN",
                            admin.getIsActive()
                    );
                }
                else if ("CUSTOMER".equals(role)) {
                    Customer customer = customerRepository.findByEmail(email)
                            .orElseThrow();
                    userDetails = new CustomUserDetails(
                            customer.getEmail(),
                            customer.getPassword(),
                            "CUSTOMER",
                            customer.getIsActive()
                    );
                }
                else if ("STAFF".equals(role)) {
                    Staff staff = staffRepository.findByEmail(email)
                            .orElseThrow();
                    userDetails = new CustomUserDetails(
                            staff.getEmail(),
                            staff.getPassword(),
                            "STAFF",
                            staff.getIsActive()
                    );
                }

                if (userDetails != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request));

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

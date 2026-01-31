package com.canteen.canteen_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.canteen.canteen_backend.dto.RestockRequestAdminDTO;
import com.canteen.canteen_backend.entity.RestockRequest;
import com.canteen.canteen_backend.entity.RestockStatus;
import com.canteen.canteen_backend.repository.RestockRequestRepository;

@Service
public class RestockRequestService {

    private final RestockRequestRepository repository;

    public RestockRequestService(RestockRequestRepository repository) {
        this.repository = repository;
    }

    public List<RestockRequestAdminDTO> getPendingRequests() {

        return repository.findByStatus(RestockStatus.PENDING)
                .stream()
                .map(r -> new RestockRequestAdminDTO(
                        r.getRequestId(),
                        r.getStaff().getStaffId(),              // staffId
                        r.getItemName(),              // ✅ itemName
                        r.getRequestedQuantity(),
                        r.getStatus().name(),
                        r.getNote(),
                        r.getRequestedAt()
                ))
                .collect(Collectors.toList());
    }
}

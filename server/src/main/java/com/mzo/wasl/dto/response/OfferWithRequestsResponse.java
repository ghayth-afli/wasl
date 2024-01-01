package com.mzo.wasl.dto.response;

import com.mzo.wasl.model.Offer;
import com.mzo.wasl.model.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OfferWithRequestsResponse {
    private Offer offer;
    private List<Request> requests;
}

package com.mzo.wasl.payload.response;

import com.mzo.wasl.models.Offer;
import com.mzo.wasl.models.Request;
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

package com.example.ubamain.dto;

import lombok.Data;

@Data
public class DataResponse {
    private int smsTotalReceived;
    private int smsTotalSuccess;
    private int smsTotalFailed;
    private int smsTotalPending;
    private int emailTotalReceived;
    private int emailTotalSuccess;
    private int emailTotalFailed;
    private int emailTotalPending;
    private int socialTotalReceived;
    private int socialTotalSuccess;
    private int socialTotalFailed;
    private int socialTotalPending;
}

package com.example.ubamain.service;

import com.example.ubamain.dto.DataResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UbaService {
    DataResponse fetchData() throws IOException;
}

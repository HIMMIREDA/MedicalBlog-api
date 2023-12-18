package com.ensa.medicalblog.service.impl;

import com.cloudinary.Cloudinary;
import com.ensa.medicalblog.service.ImageService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(Part file) throws IOException {
        return cloudinary.uploader()
                .upload(IOUtils.toByteArray(file.getInputStream()),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }
}

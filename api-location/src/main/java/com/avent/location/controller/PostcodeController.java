package com.avent.location.controller;

import com.avent.base.model.response.ResponseModel;
import com.avent.exception.EntityNotFoundException;
import com.avent.location.model.PostcodeLatLngModel;
import com.avent.location.model.request.PostcodeUpdateRequestModel;
import com.avent.location.model.response.PostcodeUpdateResponseModel;
import com.avent.location.service.PostcodeLatLngService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/postcode")
@RequiredArgsConstructor
public class PostcodeController {

    private final PostcodeLatLngService postcodeLatLngService;

    @GetMapping("/get")
    public ResponseModel<PostcodeLatLngModel> getPostcode(@RequestParam("postcode") String postcode) throws EntityNotFoundException {
        return ResponseModel.success(postcodeLatLngService.findByPostcode(postcode));
    }

    @PostMapping("/update")
    public ResponseModel<PostcodeUpdateResponseModel> updatePostcode(@Valid @RequestBody PostcodeUpdateRequestModel postcodeUpdateRequestModel) throws EntityNotFoundException {
        return ResponseModel.success(postcodeLatLngService.updatePostcode(postcodeUpdateRequestModel));
    }
}

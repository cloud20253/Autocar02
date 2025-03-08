package com.spring.jwt.SparePart;

import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/sparePartManagement")
@RestController
@RequiredArgsConstructor
public class SparePartController {

    private final SparePartService sparePartService;

//    @GetMapping("/photo/{id}")
//    public ResponseEntity<byte[]> getPhoto(@PathVariable Integer id) {
//        byte[] image = sparePartService.getPhotoById(id);
//        if (image == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(image);
//    }

    @GetMapping("/getPartById/{id}")
    public ResponseEntity<SparePartDto> getSparePartById(@PathVariable Integer id) {
        SparePartDto sparePart = sparePartService.getSparePartById(id);
        return (sparePart != null) ? ResponseEntity.ok(sparePart) : ResponseEntity.notFound().build();
    }


    @GetMapping("/getAll")
    public List<SparePartDto> getAllSpareParts() {
        return sparePartService.getAllSpareParts();
    }

    @PostMapping("/addPart")
    public ResponseEntity<BaseResponseDTO> addPart(
            @RequestParam("partName") String partName,
            @RequestParam("description") String description,
            @RequestParam("manufacturer") String manufacturer,
            @RequestParam("price") Long price,
            @RequestParam("partNumber") Long partNumber,
            @RequestParam("photos") List<MultipartFile> photos) {

        BaseResponseDTO response = sparePartService.addPart(partName, description, manufacturer, price, partNumber, photos);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<SparePartDto> updateSparePart(
            @PathVariable Integer id,
            @RequestParam(required = false) String partName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) Long partNumber,
            @RequestParam(required = false) List<MultipartFile> photos) {

        SparePartDto updatedPart = sparePartService.updatePart(id, partName, description, manufacturer, price, partNumber, photos);
        return ResponseEntity.ok(updatedPart);
    }

    @PreAuthorize("permitAll")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponseDTO> deleteSparePart(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer photoIndex) {
        BaseResponseDTO response = sparePartService.deleteSparePartById(id, photoIndex);
        return ResponseEntity.ok(response);
    }

}

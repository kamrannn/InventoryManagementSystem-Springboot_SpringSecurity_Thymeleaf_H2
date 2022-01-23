package com.app.InventorySystem.controller;

import com.app.InventorySystem.model.File;
import com.app.InventorySystem.model.dto.ResponseFile;
import com.app.InventorySystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class FileController {

    @Autowired
    private FileService storageService;

    @GetMapping("/files/single/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable(name = "id") String id) {
        File fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/upload")
    public String getUploadFileOption() {
        return "upload-file";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        if (!file.getContentType().equalsIgnoreCase("application/pdf") || file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please upload only PDF files");
        } else {
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "upload-file";
    }

    @GetMapping("/files")
    public String getListFiles(Model model) {
        List<File> files = storageService.getAllFilesFromDb();
        List<ResponseFile> responseFilesList = new ArrayList<>();
        for (File file : files
        ) {
            String fileDownload = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(file.getId()).toUriString();
            ResponseFile responseFile = new ResponseFile();
            responseFile.setId(file.getId());
            responseFile.setUrl(fileDownload);
            responseFile.setName(file.getName());
            responseFile.setSize(file.getData().length);
            responseFile.setType(file.getType());
            responseFilesList.add(responseFile);
        }

        model.addAttribute("filesList", responseFilesList);
        return "filesList";
    }

    @GetMapping("/file/delete/{id}")
    public String deleteFile(@PathVariable(name = "id") String fileId) {
        storageService.deleteFile(fileId);
        return "redirect:/files";
    }

}

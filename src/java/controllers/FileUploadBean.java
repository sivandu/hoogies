/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author I320736
 */
@ManagedBean(name = "file")
@RequestScoped
public class FileUploadBean {

    private Part uploadedFile;

    /**
     * Creates a new instance of fileUploadBean
     */
    public FileUploadBean() {
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /* Uploads a file to path with a prefix. Returns a relative path to the file */
    public String upload(String path, String prefix) throws IOException{
        FacesContext fc = FacesContext.getCurrentInstance();
        String uploadPath = fc.getExternalContext().getRealPath(path);
        File uploadDir = new File(uploadPath);
        
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        File output = new File(uploadDir, prefix + "-" + uploadedFile.getSubmittedFileName());
        try(InputStream input = uploadedFile.getInputStream()) {
            Files.copy(input, output.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        
        return path + "/" + prefix + "-" + uploadedFile.getSubmittedFileName();
    }
}

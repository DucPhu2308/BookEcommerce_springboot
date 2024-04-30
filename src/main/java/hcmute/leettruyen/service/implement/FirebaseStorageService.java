package hcmute.leettruyen.service.implement;

import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FirebaseStorageService {

    public void uploadFile(String folderName, InputStream inputStream, String fileName) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket bucket = storage.get("web-springboot-1a3ab.appspot.com");
        String storagePath = "LeetTruyen" + "/" + folderName + "/" + fileName;
        bucket.create(storagePath, inputStream, "image/jpeg");
    }
    public void deleteFile(String folderName, String fileName) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        String bucketName = "web-springboot-1a3ab.appspot.com";
        BlobId blobId = BlobId.of(bucketName, "LeetTruyen/" + folderName + "/" + fileName);
        boolean deleted = storage.delete(blobId);
        if (deleted) {
            System.out.println("Deleted successfully");
        } else {
            System.err.println("Failed to delete object");
        }
    }
}

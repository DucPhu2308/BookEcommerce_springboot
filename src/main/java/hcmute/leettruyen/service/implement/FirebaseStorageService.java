package hcmute.leettruyen.service.implement;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
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
}

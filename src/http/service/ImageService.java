package http.service;

import http.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static java.nio.file.StandardOpenOption.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    private static final ImageService INSTANCE = new ImageService();
    private static final String basePath = PropertiesUtil.get("image.base.url");

    public void upload(String imagePath, InputStream imageContent) throws IOException {
        var imageFullPath = Path.of(basePath, imagePath);

        try (imageContent) {

            // на всякий случай создаем дериктории для записи, если вдруг ее нет. Если еть - норм.
            Files.createDirectories(imageFullPath.getParent());

            // CREATE - создай, если нет
            // TRUNCATE_EXISTING - если есть - ничего страшного. Перезапиши
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}

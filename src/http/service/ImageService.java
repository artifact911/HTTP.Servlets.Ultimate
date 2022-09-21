package http.service;

import http.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

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

    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        var imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}

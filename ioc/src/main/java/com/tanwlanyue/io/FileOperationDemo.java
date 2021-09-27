package com.tanwlanyue.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei211 on 2021/9/27.
 */
public class FileOperationDemo {
    public static void main(String[] args) throws IOException {
        String pathStr = "/Users/zhanglei/Repository/toolbox";
        // Path p = Paths.get(pathStr); 等价
        Path path = FileSystems.getDefault().getPath(pathStr);
        File file = new File(pathStr);
        // file转path
        path = file.toPath();
        // path转file
        file = path.toFile();
        // 遍历
        Files.newDirectoryStream(path).forEach(e -> System.out.println(e.getFileName()));
        ArrayList<Path> list = new ArrayList<>();
        // 递归遍历
        Files.walkFileTree(path, new FindJavaVisitor(list));
        System.out.println(list.size());
        // path相关信息
        System.out.println("文件名：" + path.getFileName());
        System.out.println("名称元素的数量：" + path.getNameCount());
        System.out.println("父路径：" + path.getParent());
        System.out.println("根路径：" + path.getRoot());
        System.out.println("是否是绝对路径：" + path.isAbsolute());
        System.out.println("是否是以为给定的路径/Users开始：" + path.startsWith("/Users"));
        System.out.println("该路径的字符串形式：" + path.toString());

        path = Paths.get("./ioc/src/main/java");
        System.out.println(path.toAbsolutePath());
        System.out.println(path.toAbsolutePath().normalize());
        System.out.println(path.toRealPath());

        // 检测时不包含符号链接文件
        boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println(exists);

        // 创建文件
        path = Paths.get("./ioc/src/test/java/test.md");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Path to = Paths.get("./ioc/src/test/java/replication.md");

        if (!Files.exists(to)) {
            // 复制文件
            Files.copy(path, to);
        }
        // 覆盖文件
        Files.copy(path, to, StandardCopyOption.REPLACE_EXISTING);
        Files.deleteIfExists(to);

        Files.deleteIfExists(path);
        // 创建文件
        path = Paths.get("./ioc/src/test/java/io");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        // 删除文件或目录
        Files.deleteIfExists(path);
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;

        public FindJavaVisitor(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toString().endsWith(".java")) {
                result.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

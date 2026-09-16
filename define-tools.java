package com.example.demo.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GithubTools {

    private final GitHub gitHub;

    public GithubTools(@Value("${github.token}") String token) throws Exception {
        this.gitHub = GitHub.connectUsingOAuth(token);
    }

    @Tool("Read a Java file from a GitHub repository")
    public String readJavaFile(
            @P("Repository in owner/repo format") String repoName,
            @P("Relative path to the file") String filePath) {
        try {
            return gitHub.getRepository(repoName).getFileContent(filePath).getContent();
        } catch (Exception e) {
            return "Error reading file: " + e.getMessage();
        }
    }

    @Tool("Write or update a Java file in a GitHub repository")
    public String writeJavaFile(
            @P("Repository in owner/repo format") String repoName,
            @P("Relative path to the file") String filePath,
            @P("Complete Java source code") String content,
            @P("Commit message") String message) {
        try {
            GHRepository repo = gitHub.getRepository(repoName);
            try {
                GHContent file = repo.getFileContent(filePath);
                file.update(content, message);
                return "Updated file: " + filePath;
            } catch (GHFileNotFoundException e) {
                repo.createContent().path(filePath).content(content).message(message).commit();
                return "Created file: " + filePath;
            }
        } catch (Exception e) {
            return "Error writing file: " + e.getMessage();
        }
    }
}
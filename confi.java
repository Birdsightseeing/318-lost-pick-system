package com.example.demo.config;

import com.example.demo.agent.JavaDeveloperAgent;
import com.example.demo.tools.GithubTools;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    public JavaDeveloperAgent javaDeveloperAgent(
            @Value("${langchain4j.open-ai.chat-model.api-key}") String apiKey,
            GithubTools githubTools) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o")
                .temperature(0.2)
                .build();

        return AiServices.builder(JavaDeveloperAgent.class)
                .chatLanguageModel(model)
                .tools(githubTools)
                .build();
    }
}
package com.gms.gms.appsecurity.service;



import com.gms.gms.appsecurity.entity.Token;
import com.gms.gms.appsecurity.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TokenService {
    private final TokenRepository tokenRepository;

    @Scheduled(fixedRate = 43200000)
    public void updateObject() throws Exception {
        try {
            List<Token> tokens = tokenRepository.findByExpired(false);
            if (!tokens.isEmpty()) {

                 List<Token> updatedTokens = tokens.stream()
                        .filter(authToken -> (authToken.getCreatedAt().plusDays(1)).isBefore(LocalDateTime.now())
                        )
                        .peek(authToken -> authToken.setExpired(true))
                        .toList();
                tokenRepository.saveAll(updatedTokens);

            }
        } catch (Exception ex) {
            throw new Exception("Failed to update token ");

        }
    }

    @Scheduled(fixedRate = 43200000)
    public void deleteExpired() throws Exception {
        try{
            tokenRepository.deleteExpiredTokens();
        } catch (Exception ex) {
            throw new Exception("Failed to delete token ");

        }
    }
}

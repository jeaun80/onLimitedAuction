package com.example.onlimitedauction.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@NoArgsConstructor
public class KeyGenerator {

    public static String streamKeyGenerator(){
        UUID u=UUID.randomUUID();
        return String.valueOf(u);
    }

}

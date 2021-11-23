package com.huang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authour huang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {

    private int id;

    private String username;

    private String password;

    private String perm;
}

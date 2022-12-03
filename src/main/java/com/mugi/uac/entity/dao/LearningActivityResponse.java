/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugi.uac.entity.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author saqlever
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LearningActivityResponse {

    private String userId;
    private String contentUuid;
    private String contentTitle;
    private String status;
    private String completedDate;
    private String firstAccess;
    private String highScore;

}

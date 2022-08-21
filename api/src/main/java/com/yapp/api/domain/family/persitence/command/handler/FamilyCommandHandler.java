package com.yapp.api.domain.family.persitence.command.handler;

import com.yapp.realtime.entity.family.persistence.entity.Family;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FamilyCommandHandler {
    Family save(Family family);
}

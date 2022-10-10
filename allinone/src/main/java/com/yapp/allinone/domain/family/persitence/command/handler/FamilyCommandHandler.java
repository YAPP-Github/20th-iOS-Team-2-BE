package com.yapp.allinone.domain.family.persitence.command.handler;

import com.yapp.supporter.entity.family.persistence.entity.Family;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public interface FamilyCommandHandler {
    Family save(Family family);
}

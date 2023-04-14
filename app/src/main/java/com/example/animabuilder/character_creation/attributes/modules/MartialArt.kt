package com.example.animabuilder.character_creation.attributes.modules

/**
 * Art that the character can buy for (primarily) unarmed combat.
 *
 * @param name name of the art
 * @param description details of the arts combat effects
 * @param prereqList prerequisites for having this art
 * @param mkBonus additional martial knowledge the character may gain from having this art
 */
class MartialArt (
    var name: String,
    var description: String,
    var prereqList: String,
    var mkBonus: Int
)
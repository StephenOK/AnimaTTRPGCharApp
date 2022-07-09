package com.example.animabuilder.CharacterCreation.Attributes;

import android.content.Context;

import com.example.animabuilder.R;

import java.util.Arrays;

public class CharClass {
    int classIndex;

    public int getClassIndex(){return classIndex;}

    int lifePointMultiple;
    int lifePointsPerLevel;
    int initiativePerLevel;
    int mkPerLevel;
    int psyPerTurn;

    public int getLifePointMultiple(){return lifePointMultiple;}
    public int getLifePointsPerLevel(){return lifePointsPerLevel;}
    public int getInitiativePerLevel(){return initiativePerLevel;}
    public int getMkPerLevel(){return mkPerLevel;}
    public int getpsyPerTurn(){return psyPerTurn;}






    //primary ability block
    //combat block
    double combatMax;
    public double getCombatMax(){return combatMax;}

    int atkGrowth;
    int blockGrowth;
    int dodgeGrowth;
    int armorGrowth;
    int kiGrowth;
    int kiAccumMult;

    public int getAtkGrowth(){return atkGrowth;}
    public int getBlockGrowth(){return blockGrowth;}
    public int getDodgeGrowth(){return dodgeGrowth;}
    public int getArmorGrowth(){return armorGrowth;}
    public int getKiGrowth() {return kiGrowth;}
    public int getKiAccumMult(){return kiAccumMult;}






    //supernatural block
    double magMax;
    public double getMagMax(){return magMax;}

    int zeonGrowth;
    int maGrowth;
    int maProjGrowth;
    int summonGrowth;
    int controlGrowth;
    int bindGrowth;
    int banishGrowth;

    public int getZeonGrowth(){return zeonGrowth;}
    public int getMaGrowth(){return maGrowth;}
    public int getMaProjGrowth(){return maProjGrowth;}
    public int getSummonGrowth(){return summonGrowth;}
    public int getControlGrowth(){return controlGrowth;}
    public int getBindGrowth(){return bindGrowth;}
    public int getBanishGrowth(){return banishGrowth;}






    //psychic block
    double psyMax;
    public double getPsyMax(){return psyMax;}

    int psyPointGrowth;
    int psyProjGrowth;

    public int getPsyPointGrowth(){return psyPointGrowth;}
    public int getPsyProjGrowth(){return psyProjGrowth;}






    //secondary ability block
    int athGrowth;
    int socGrowth;
    int percGrowth;
    int intellGrowth;
    int vigGrowth;
    int subterGrowth;
    int creatGrowth;

    public int getAthGrowth(){return athGrowth;}
    public int getSocGrowth(){return socGrowth;}
    public int getPercGrowth(){return percGrowth;}
    public int getIntellGrowth(){return intellGrowth;}
    public int getVigGrowth(){return vigGrowth;}
    public int getSubterGrowth(){return subterGrowth;}
    public int getCreatGrowth(){return creatGrowth;}






    //reduced cost block
    int strengthFeatReduction;
    int standPainReduction;
    int composeReduction;
    int trapReduction;
    int herbalReduction;
    int animalReduction;
    int medReduction;
    int appraiseReduction;
    int stealthReduction;
    int memorizeReduction;
    int magAppraiseReduction;
    int sleightReduction;
    int persuadeReduction;
    int occultReduction;

    public int getStrengthFeatReduction(){return strengthFeatReduction;}
    public int getStandPainReduction(){return standPainReduction;}
    public int getComposeReduction(){return composeReduction;}
    public int getTrapReduction(){return trapReduction;}
    public int getHerbalReduction(){return herbalReduction;}
    public int getAnimalReduction(){return animalReduction;}
    public int getMedReduction(){return medReduction;}
    public int getAppraiseReduction(){return appraiseReduction;}
    public int getStealthReduction(){return stealthReduction;}
    public int getMemorizeReduction(){return memorizeReduction;}
    public int getMagAppraiseReduction(){return magAppraiseReduction;}
    public int getSleightReduction(){return sleightReduction;}
    public int getPersuadeReduction(){return persuadeReduction;}
    public int getOccultReduction(){return occultReduction;}






    //innate bonus block
    //primaries
    int atkPerLevel;
    int blockPerLevel;
    int dodgePerLevel;
    int armorPerLevel;

    public int getAtkPerLevel(){return atkPerLevel;}
    public int getBlockPerLevel(){return blockPerLevel;}
    public int getDodgePerLevel(){return dodgePerLevel;}
    public int getArmorPerLevel(){return armorPerLevel;}






    int zeonPerLevel;
    int summonPerLevel;
    int controlPerLevel;
    int bindPerLevel;
    int banishPerLevel;

    public int getZeonPerLevel(){return zeonPerLevel;}
    public int getSummonPerLevel(){return summonPerLevel;}
    public int getControlPerLevel(){return controlPerLevel;}
    public int getBindPerLevel(){return bindPerLevel;}
    public int getBanishPerLevel(){return banishPerLevel;}






    //secondaries
    int acrobatPerLevel;
    int athletPerLevel;
    int climbPerLevel;
    int jumpPerLevel;
    int ridePerLevel;
    int swimPerLevel;

    public int getAcrobatPerLevel(){return acrobatPerLevel;}
    public int getAthletPerLevel(){return athletPerLevel;}
    public int getClimbPerLevel(){return climbPerLevel;}
    public int getJumpPerLevel(){return jumpPerLevel;}
    public int getRidePerLevel(){return ridePerLevel;}
    public int getSwimPerLevel(){return swimPerLevel;}






    int artPerLevel;
    int dancePerLevel;
    int forgePerLevel;
    int musicPerLevel;
    int sleightPerLevel;

    public int getArtPerLevel(){return artPerLevel;}
    public int getDancePerLevel(){return dancePerLevel;}
    public int getForgePerLevel(){return forgePerLevel;}
    public int getMusicPerLevel(){return musicPerLevel;}
    public int getSleightPerLevel(){return sleightPerLevel;}






    int noticePerLevel;
    int searchPerLevel;
    int trackPerLevel;

    public int getNoticePerLevel(){return noticePerLevel;}
    public int getSearchPerLevel(){return searchPerLevel;}
    public int getTrackPerLevel(){return trackPerLevel;}






    int intimidatePerLevel;
    int leaderPerLevel;
    int persuadePerLevel;
    int stylePerLevel;

    public int getIntimidatePerLevel(){return intimidatePerLevel;}
    public int getLeaderPerLevel(){return leaderPerLevel;}
    public int getPersuadePerLevel(){return persuadePerLevel;}
    public int getStylePerLevel(){return stylePerLevel;}






    int disguisePerLevel;
    int hidePerLevel;
    int lockpickPerLevel;
    int poisonPerLevel;
    int theftPerLevel;
    int stealthPerLevel;
    int trapPerLevel;

    public int getDisguisePerLevel(){return disguisePerLevel;}
    public int getHidePerLevel(){return hidePerLevel;}
    public int getLockpickPerLevel(){return lockpickPerLevel;}
    public int getPoisonPerLevel(){return poisonPerLevel;}
    public int getTheftPerLevel(){return theftPerLevel;}
    public int getStealthPerLevel(){return stealthPerLevel;}
    public int getTrapPerLevel(){return trapPerLevel;}






    int animalPerLevel;
    int appraisePerLevel;
    int herbPerLevel;
    int histPerLevel;
    int memorizePerLevel;
    int magAppraisePerLevel;
    int medicPerLevel;
    int navPerLevel;
    int occultPerLevel;
    int sciencePerLevel;

    public int getAnimalPerLevel(){return animalPerLevel;}
    public int getAppraisePerLevel(){return appraisePerLevel;}
    public int getHerbPerLevel(){return herbPerLevel;}
    public int getHistPerLevel(){return histPerLevel;}
    public int getMemorizePerLevel(){return memorizePerLevel;}
    public int getMagAppraisePerLevel(){return magAppraisePerLevel;}
    public int getMedicPerLevel(){return medicPerLevel;}
    public int getNavPerLevel(){return navPerLevel;}
    public int getOccultPerLevel(){return occultPerLevel;}
    public int getSciencePerLevel(){return sciencePerLevel;}






    int composePerLevel;
    int strengthFeatPerLevel;
    int standPainPerLevel;

    public int getComposePerLevel(){return composePerLevel;}
    public int getStrengthFeatPerLevel(){return composePerLevel;}
    public int getStandPainPerLevel(){return standPainPerLevel;}






    public CharClass(ClassName classType){
        int [] statBlock;
        switch(classType){
            case warrior:
                classIndex = 1;

                statBlock = new int[]{15, 15, 5, 25, 3,
                                        60, 2, 2, 2, 2, 2, 20,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 2, 3, 2, 2, 2};

                strengthFeatReduction = 1;

                atkPerLevel = 5;
                blockPerLevel = 5;
                armorPerLevel = 5;

                strengthFeatPerLevel = 5;

                break;

            case acroWarrior:
                classIndex = 2;

                statBlock = new int[]{20, 10, 10, 25, 3,
                                        60, 2, 3, 2, 2, 2, 20,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 2, 3, 2, 2, 2};

                atkPerLevel = 5;
                dodgePerLevel = 5;

                acrobatPerLevel = 10;
                jumpPerLevel = 10;
                athletPerLevel = 10;
                sleightPerLevel = 10;
                stylePerLevel = 10;

                break;

            case paladin:
                classIndex = 3;

                statBlock = new int[]{15, 15, 5, 20, 3,
                                        60, 2, 2, 2, 2, 2, 20,
                                        50, 2, 60, 3, 3, 3, 3, 1,
                                        50, 20, 3,
                                        2, 1, 2, 2, 2, 3, 2};

                standPainReduction = 1;

                blockPerLevel = 5;
                armorPerLevel = 10;
                banishPerLevel = 10;
                zeonPerLevel = 20;

                leaderPerLevel = 10;
                standPainPerLevel = 10;
                stylePerLevel = 5;

                break;

            case darkPaladin:
                classIndex = 4;

                statBlock = new int[]{15, 15, 5, 20, 3,
                                        60, 2, 2, 2, 2, 2, 20,
                                        50, 2, 60, 3, 3, 1, 3, 3,
                                        50, 20, 3,
                                        2, 1, 2, 2, 2, 2, 2};

                composeReduction = 1;

                atkPerLevel = 5;
                armorPerLevel = 5;
                controlPerLevel = 10;
                zeonPerLevel = 20;

                intimidatePerLevel = 10;
                composePerLevel = 10;
                stylePerLevel = 5;
                persuadePerLevel = 5;

                break;

            case weaponMaster:
                classIndex = 5;

                statBlock = new int[]{10, 20, 5, 10, 3,
                                        60, 2, 2, 2, 1, 3, 30,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 2, 3, 1, 3, 2};

                atkPerLevel = 5;
                blockPerLevel = 5;
                armorPerLevel = 10;

                strengthFeatPerLevel = 5;

                break;

            case technician:
                classIndex = 6;

                statBlock = new int[]{20, 5, 5, 50, 3,
                                        60, 2, 2, 2, 2, 1, 10,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 2, 3, 2, 2, 2};

                atkPerLevel = 5;

                break;

            case tao:
                classIndex = 7;

                statBlock = new int[]{20, 10, 5, 30, 3,
                                        60, 2, 2, 2, 2, 2, 15,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 2, 3, 2, 2, 2};

                stylePerLevel = 5;

                break;

            case ranger:
                classIndex = 8;

                statBlock = new int[]{20, 10, 5, 20, 3,
                                        60, 2, 2, 2, 2, 2, 25,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 1, 3, 3, 2, 2};

                trapReduction = 1;
                herbalReduction = 2;
                animalReduction = 1;
                medReduction = 2;

                atkPerLevel = 5;

                noticePerLevel = 10;
                searchPerLevel = 10;
                trackPerLevel = 10;
                trapPerLevel = 5;
                animalPerLevel = 5;
                herbPerLevel = 5;

                break;

            case shadow:
                classIndex = 9;

                statBlock = new int[]{20, 5, 10, 25, 3,
                                        60, 2, 3, 2, 2, 2, 20,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 2, 3, 2, 2, 2};

                atkPerLevel = 5;
                dodgePerLevel = 5;

                noticePerLevel = 10;
                searchPerLevel = 10;
                hidePerLevel = 10;
                stealthPerLevel = 10;

                break;

            case thief:
                classIndex = 10;

                statBlock = new int[]{20, 5, 10, 20, 3,
                                        50, 2, 3, 2, 3, 2, 25,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        1, 2, 2, 3, 3, 1, 2};

                appraiseReduction = 1;

                dodgePerLevel = 5;

                noticePerLevel = 5;
                searchPerLevel = 5;
                hidePerLevel = 5;
                stealthPerLevel = 5;
                trapPerLevel = 5;
                sleightPerLevel = 5;
                theftPerLevel = 10;

                break;

            case assassin:
                classIndex = 11;

                statBlock = new int[]{20, 5, 10, 20, 3,
                                        50, 2, 3, 2, 3, 2, 25,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 1, 3, 3, 2, 2};

                stealthReduction = 1;
                composeReduction = 2;
                memorizeReduction = 2;

                atkPerLevel = 5;

                noticePerLevel = 10;
                searchPerLevel = 10;
                hidePerLevel = 10;
                stealthPerLevel = 10;
                poisonPerLevel = 10;
                composePerLevel = 10;
                trapPerLevel = 10;

                break;

            case wizard:
                classIndex = 12;

                statBlock = new int[]{20, 5, 5, 10, 3,
                                        50, 3, 3, 2, 3, 3, 30,
                                        60, 1, 50, 2, 2, 2, 2, 2,
                                        50, 20, 3,
                                        2, 2, 2, 2, 3, 2, 2};

                magAppraiseReduction = 1;

                zeonPerLevel = 100;

                magAppraisePerLevel = 10;
                occultPerLevel = 5;

                break;

            case warlock:
                classIndex = 13;

                statBlock = new int[]{20, 10, 5, 20, 3,
                                        50, 2, 2, 2, 2, 2, 25,
                                        50, 1, 50, 2, 2, 2, 2, 2,
                                        50, 20, 3,
                                        2, 2, 2, 2, 2, 2, 2};

                atkPerLevel = 5;
                blockPerLevel = 5;
                dodgePerLevel = 5;
                zeonPerLevel = 20;

                magAppraisePerLevel = 5;

                break;

            case illusionist:
                classIndex = 14;

                statBlock = new int[]{20, 5, 5, 20, 3,
                                        50, 3, 3, 2, 3, 2, 25,
                                        60, 1, 60, 2, 3, 3, 3, 3,
                                        50, 20, 3,
                                        2, 2, 2, 2, 3, 2, 2};

                sleightReduction = 1;
                persuadeReduction = 1;

                zeonPerLevel = 75;

                magAppraisePerLevel = 5;
                stealthPerLevel = 10;
                hidePerLevel = 10;
                sleightPerLevel = 10;
                disguisePerLevel = 5;
                theftPerLevel = 5;
                persuadePerLevel = 5;

                break;

            case wizMentalist:
                classIndex = 15;

                statBlock = new int[]{20, 5, 5, 10, 1,
                                        50, 3, 3, 2, 3, 3, 30,
                                        50, 1, 50, 2, 2, 2, 2, 2,
                                        50, 10, 2,
                                        2, 2, 2, 2, 3, 2, 2};

                zeonPerLevel = 100;

                magAppraisePerLevel = 10;
                occultPerLevel = 5;

                break;

            case summoner:
                classIndex = 16;

                statBlock = new int[]{20, 5, 5, 10, 3,
                                        50, 3, 3, 2, 3, 3, 30,
                                        60, 1, 60, 3, 1, 1, 1, 1,
                                        50, 20, 3,
                                        2, 2, 2, 2, 3, 2, 2};

                occultReduction = 1;

                zeonPerLevel = 50;
                summonPerLevel = 10;
                controlPerLevel = 10;
                bindPerLevel = 10;
                banishPerLevel = 10;

                magAppraisePerLevel = 5;
                occultPerLevel = 10;

                break;

            case warSummoner:
                classIndex = 17;

                statBlock = new int[]{20, 10, 5, 20, 3,
                                        50, 2, 2, 2, 2, 2,
                                        50, 1, 60, 3, 1, 1, 1, 1,
                                        50, 20, 3,
                                        2, 2, 2, 2, 2, 2, 2};

                atkPerLevel = 5;
                blockPerLevel = 5;
                dodgePerLevel = 5;
                zeonPerLevel = 20;
                summonPerLevel = 5;
                controlPerLevel = 5;
                bindPerLevel = 5;
                banishPerLevel = 5;

                occultPerLevel = 5;

                break;

            case mentalist:
                classIndex = 18;

                statBlock = new int[]{20, 5, 5, 10, 1,
                                        50, 3, 3, 2, 3, 3, 30,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        60, 10, 2,
                                        2, 2, 2, 2, 3, 2, 2};
                break;

            case warMentalist:
                classIndex = 19;

                statBlock = new int[]{20, 10, 5, 20, 1,
                                        50, 2, 2, 2, 2, 2, 25,
                                        50, 3, 70, 3, 3, 3, 3, 3,
                                        50, 15, 2,
                                        2, 2, 2, 3, 2, 2, 2};

                atkPerLevel = 5;
                blockPerLevel = 5;
                dodgePerLevel = 5;

                break;

            default:
                classIndex = 0;

                statBlock = new int[]{20, 5, 5, 20, 2,
                                        60, 2, 2, 2, 2, 2, 20,
                                        60, 2, 60, 2, 2, 2, 2, 2,
                                        60, 20, 2,
                                        2, 2, 2, 2, 2, 2, 2};

                zeonPerLevel = 10;
        }

        setClassBase(statBlock);
    }

    private void setClassBase(int[] statList){
        lifePointMultiple = statList[0];
        lifePointsPerLevel = statList[1];
        initiativePerLevel = statList[2];
        mkPerLevel = statList[3];
        psyPerTurn = statList[4];

        combatMax = statList[5]/100.0;
        atkGrowth = statList[6];
        blockGrowth = statList[7];
        dodgeGrowth = statList[8];
        armorGrowth = statList[9];
        kiGrowth = statList[10];
        kiAccumMult = statList[11];

        magMax = statList[12]/100.0;
        zeonGrowth = statList[13];
        maGrowth = statList[14];
        maProjGrowth = statList[15];
        summonGrowth = statList[16];
        controlGrowth = statList[17];
        bindGrowth = statList[18];
        banishGrowth = statList[19];

        psyMax = statList[20]/100.0;
        psyPointGrowth = statList[21];
        psyProjGrowth = statList[22];

        athGrowth = statList[23];
        socGrowth = statList[24];
        percGrowth = statList[25];
        intellGrowth = statList[26];
        vigGrowth = statList[27];
        subterGrowth = statList[28];
        creatGrowth = statList[29];
    }
}

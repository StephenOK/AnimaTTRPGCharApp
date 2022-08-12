package com.example.animabuilder.CharacterCreation.Attributes;

import java.io.Serializable;

public class SecondaryList implements Serializable {
    int lvl;

    //athletics
    SecondaryCharacteristic acrobatics;
    SecondaryCharacteristic athletics;
    SecondaryCharacteristic climb;
    SecondaryCharacteristic jump;
    SecondaryCharacteristic ride;
    SecondaryCharacteristic swim;

    public SecondaryCharacteristic getAcrobatics(){return acrobatics;}
    public SecondaryCharacteristic getAthletics(){return athletics;}
    public SecondaryCharacteristic getClimb(){return climb;}
    public SecondaryCharacteristic getJump(){return jump;}
    public SecondaryCharacteristic getRide(){return ride;}
    public SecondaryCharacteristic getSwim(){return swim;}

    //creative
    SecondaryCharacteristic art;
    SecondaryCharacteristic dance;
    SecondaryCharacteristic forging;
    SecondaryCharacteristic music;
    SecondaryCharacteristic sleightHand;

    public SecondaryCharacteristic getArt(){return art;}
    public SecondaryCharacteristic getDance(){return dance;}
    public SecondaryCharacteristic getForging(){return forging;}
    public SecondaryCharacteristic getMusic(){return music;}
    public SecondaryCharacteristic getSleightHand(){return sleightHand;}

    //perceptive
    SecondaryCharacteristic notice;
    SecondaryCharacteristic search;
    SecondaryCharacteristic track;

    public SecondaryCharacteristic getNotice(){return notice;}
    public SecondaryCharacteristic getSearch(){return search;}
    public SecondaryCharacteristic getTrack(){return track;}

    //social
    SecondaryCharacteristic intimidate;
    SecondaryCharacteristic leadership;
    SecondaryCharacteristic persuasion;
    SecondaryCharacteristic style;

    public SecondaryCharacteristic getIntimidate(){return intimidate;}
    public SecondaryCharacteristic getLeadership(){return leadership;}
    public SecondaryCharacteristic getPersuasion(){return persuasion;}
    public SecondaryCharacteristic getStyle(){return style;}

    //subterfuge
    SecondaryCharacteristic disguise;
    SecondaryCharacteristic hide;
    SecondaryCharacteristic lockPick;
    SecondaryCharacteristic poisons;
    SecondaryCharacteristic theft;
    SecondaryCharacteristic stealth;
    SecondaryCharacteristic trapLore;

    public SecondaryCharacteristic getDisguise(){return disguise;}
    public SecondaryCharacteristic getHide(){return hide;}
    public SecondaryCharacteristic getLockPick(){return lockPick;}
    public SecondaryCharacteristic getPoisons(){return poisons;}
    public SecondaryCharacteristic getTheft(){return theft;}
    public SecondaryCharacteristic getStealth(){return stealth;}
    public SecondaryCharacteristic getTrapLore(){return trapLore;}

    //intellectual
    SecondaryCharacteristic animals;
    SecondaryCharacteristic appraise;
    SecondaryCharacteristic herbalLore;
    SecondaryCharacteristic history;
    SecondaryCharacteristic memorize;
    SecondaryCharacteristic magicAppraise;
    SecondaryCharacteristic medic;
    SecondaryCharacteristic navigate;
    SecondaryCharacteristic occult;
    SecondaryCharacteristic sciences;

    public SecondaryCharacteristic getAnimals(){return animals;}
    public SecondaryCharacteristic getAppraise(){return appraise;}
    public SecondaryCharacteristic getHerbalLore(){return herbalLore;}
    public SecondaryCharacteristic getHistory(){return history;}
    public SecondaryCharacteristic getMemorize(){return memorize;}
    public SecondaryCharacteristic getMagicAppraise(){return magicAppraise;}
    public SecondaryCharacteristic getMedic(){return medic;}
    public SecondaryCharacteristic getNavigate(){return navigate;}
    public SecondaryCharacteristic getOccult(){return occult;}
    public SecondaryCharacteristic getSciences(){return sciences;}

    //vigor
    SecondaryCharacteristic composure;
    SecondaryCharacteristic strengthFeat;
    SecondaryCharacteristic resistPain;

    public SecondaryCharacteristic getComposure(){return composure;}
    public SecondaryCharacteristic getStrengthFeat(){return strengthFeat;}
    public SecondaryCharacteristic getResistPain(){return resistPain;}

    public SecondaryList(){
        acrobatics = new SecondaryCharacteristic();
        athletics = new SecondaryCharacteristic();
        climb = new SecondaryCharacteristic();
        jump = new SecondaryCharacteristic();
        ride = new SecondaryCharacteristic();
        swim = new SecondaryCharacteristic();

        art = new SecondaryCharacteristic();
        dance = new SecondaryCharacteristic();
        forging = new SecondaryCharacteristic();
        music = new SecondaryCharacteristic();
        sleightHand = new SecondaryCharacteristic();

        notice = new SecondaryCharacteristic();
        search = new SecondaryCharacteristic();
        track = new SecondaryCharacteristic();

        intimidate = new SecondaryCharacteristic();
        leadership = new SecondaryCharacteristic();
        persuasion = new SecondaryCharacteristic();
        style = new SecondaryCharacteristic();

        disguise = new SecondaryCharacteristic();
        hide = new SecondaryCharacteristic();
        lockPick = new SecondaryCharacteristic();
        poisons = new SecondaryCharacteristic();
        theft = new SecondaryCharacteristic();
        stealth = new SecondaryCharacteristic();
        trapLore = new SecondaryCharacteristic();

        animals = new SecondaryCharacteristic();
        appraise = new SecondaryCharacteristic();
        herbalLore = new SecondaryCharacteristic();
        history = new SecondaryCharacteristic();
        memorize = new SecondaryCharacteristic();
        magicAppraise = new SecondaryCharacteristic();
        medic = new SecondaryCharacteristic();
        navigate = new SecondaryCharacteristic();
        occult = new SecondaryCharacteristic();
        sciences = new SecondaryCharacteristic();

        composure = new SecondaryCharacteristic();
        strengthFeat = new SecondaryCharacteristic();
        resistPain = new SecondaryCharacteristic();
    }

    public void levelUpdate(int lvl, CharClass heldClass){
        this.lvl = lvl;
        classUpdate(heldClass);
    }

    public void classUpdate(CharClass newClass){
        acrobatics.setPointsFromClass(newClass.getAcrobatPerLevel() * lvl);
        athletics.setPointsFromClass(newClass.getAthletPerLevel() * lvl);
        climb.setPointsFromClass(newClass.getClimbPerLevel() * lvl);
        jump.setPointsFromClass(newClass.getJumpPerLevel() * lvl);
        ride.setPointsFromClass(newClass.getRidePerLevel() * lvl);
        swim.setPointsFromClass(newClass.getSwimPerLevel() * lvl);

        art.setPointsFromClass(newClass.getArtPerLevel() * lvl);
        dance.setPointsFromClass(newClass.getDancePerLevel() * lvl);
        forging.setPointsFromClass(newClass.getForgePerLevel() * lvl);
        music.setPointsFromClass(newClass.getMusicPerLevel() * lvl);
        sleightHand.setPointsFromClass(newClass.getSleightPerLevel() * lvl);

        notice.setPointsFromClass(newClass.getNoticePerLevel() * lvl);
        search.setPointsFromClass(newClass.getSearchPerLevel() * lvl);
        track.setPointsFromClass(newClass.getTrackPerLevel() * lvl);

        intimidate.setPointsFromClass(newClass.getIntimidatePerLevel() * lvl);
        leadership.setPointsFromClass(newClass.getLeaderPerLevel() * lvl);
        persuasion.setPointsFromClass(newClass.getPersuadePerLevel() * lvl);
        style.setPointsFromClass(newClass.getStylePerLevel() * lvl);

        disguise.setPointsFromClass(newClass.getDisguisePerLevel() * lvl);
        hide.setPointsFromClass(newClass.getHidePerLevel() * lvl);
        lockPick.setPointsFromClass(newClass.getLockpickPerLevel() * lvl);
        poisons.setPointsFromClass(newClass.getPoisonPerLevel() * lvl);
        theft.setPointsFromClass(newClass.getTheftPerLevel() * lvl);
        stealth.setPointsFromClass(newClass.getStealthPerLevel() * lvl);
        trapLore.setPointsFromClass(newClass.getTrapPerLevel() * lvl);

        animals.setPointsFromClass(newClass.getAnimalPerLevel() * lvl);
        appraise.setPointsFromClass(newClass.getAppraisePerLevel() * lvl);
        herbalLore.setPointsFromClass(newClass.getHerbPerLevel() * lvl);
        history.setPointsFromClass(newClass.getHistPerLevel() * lvl);
        memorize.setPointsFromClass(newClass.getMemorizePerLevel() * lvl);
        magicAppraise.setPointsFromClass(newClass.getMagAppraisePerLevel() * lvl);
        medic.setPointsFromClass(newClass.getMedicPerLevel() * lvl);
        navigate.setPointsFromClass(newClass.getNavPerLevel() * lvl);
        occult.setPointsFromClass(newClass.getOccultPerLevel() * lvl);
        sciences.setPointsFromClass(newClass.getSciencePerLevel() * lvl);

        composure.setPointsFromClass(newClass.getComposePerLevel() * lvl);
        strengthFeat.setPointsFromClass(newClass.getStrengthFeatPerLevel() * lvl);
        resistPain.setPointsFromClass(newClass.getStandPainPerLevel() * lvl);
    }

    public void updateSTR(int strMod){
        jump.setModVal(strMod);
        strengthFeat.setModVal(strMod);
    }

    public void updateDEX(int dexMod){
        forging.setModVal(dexMod);
        sleightHand.setModVal(dexMod);

        disguise.setModVal(dexMod);
        lockPick.setModVal(dexMod);
        theft.setModVal(dexMod);
        trapLore.setModVal(dexMod);
    }

    public void updateAGI(int agiMod){
        acrobatics.setModVal(agiMod);
        athletics.setModVal(agiMod);
        climb.setModVal(agiMod);
        ride.setModVal(agiMod);
        swim.setModVal(agiMod);

        dance.setModVal(agiMod);
        stealth.setModVal(agiMod);
    }

    public void updateINT(int intMod){
        persuasion.setModVal(intMod);

        poisons.setModVal(intMod);

        animals.setModVal(intMod);
        appraise.setModVal(intMod);
        herbalLore.setModVal(intMod);
        history.setModVal(intMod);
        memorize.setModVal(intMod);
        medic.setModVal(intMod);
        navigate.setModVal(intMod);
        occult.setModVal(intMod);
        sciences.setModVal(intMod);
    }

    public void updatePOW(int powMod){
        art.setModVal(powMod);
        music.setModVal(powMod);

        leadership.setModVal(powMod);
        style.setModVal(powMod);

        magicAppraise.setModVal(powMod);
    }

    public void updateWP(int wpMod){
        intimidate.setModVal(wpMod);

        composure.setModVal(wpMod);
        resistPain.setModVal(wpMod);
    }

    public void updatePER(int perMod){
        notice.setModVal(perMod);
        search.setModVal(perMod);
        track.setModVal(perMod);

        hide.setModVal(perMod);
    }
}

package com.example.animabuilder.CharacterCreation.Attributes;

import android.widget.Toast;

import com.example.animabuilder.CharacterCreation.SerialOutputStream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class SecondaryList implements Serializable {
    int lvl;
    int currentNatTaken;

    public boolean incrementNat(boolean direction){
        if(direction){
            if(currentNatTaken < lvl) {
                currentNatTaken++;
                return true;
            }
            else
                return false;
        }
        else
            currentNatTaken--;

        return true;
    }

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
        currentNatTaken = 0;

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
        acrobatics.setDevPerPoint(newClass.getAthGrowth());
        athletics.setPointsFromClass(newClass.getAthletPerLevel() * lvl);
        athletics.setDevPerPoint(newClass.getAthGrowth());
        climb.setPointsFromClass(newClass.getClimbPerLevel() * lvl);
        climb.setDevPerPoint(newClass.getAthGrowth());
        jump.setPointsFromClass(newClass.getJumpPerLevel() * lvl);
        jump.setDevPerPoint(newClass.getAthGrowth());
        ride.setPointsFromClass(newClass.getRidePerLevel() * lvl);
        ride.setDevPerPoint(newClass.getAthGrowth());
        swim.setPointsFromClass(newClass.getSwimPerLevel() * lvl);
        swim.setDevPerPoint(newClass.getAthGrowth());

        art.setPointsFromClass(newClass.getArtPerLevel() * lvl);
        art.setDevPerPoint(newClass.getCreatGrowth());
        dance.setPointsFromClass(newClass.getDancePerLevel() * lvl);
        dance.setDevPerPoint(newClass.getCreatGrowth());
        forging.setPointsFromClass(newClass.getForgePerLevel() * lvl);
        forging.setDevPerPoint(newClass.getCreatGrowth());
        music.setPointsFromClass(newClass.getMusicPerLevel() * lvl);
        music.setDevPerPoint(newClass.getCreatGrowth());
        sleightHand.setPointsFromClass(newClass.getSleightPerLevel() * lvl);
        sleightHand.setDevPerPoint(newClass.getCreatGrowth());

        notice.setPointsFromClass(newClass.getNoticePerLevel() * lvl);
        notice.setDevPerPoint(newClass.getPercGrowth());
        search.setPointsFromClass(newClass.getSearchPerLevel() * lvl);
        search.setDevPerPoint(newClass.getPercGrowth());
        track.setPointsFromClass(newClass.getTrackPerLevel() * lvl);
        track.setDevPerPoint(newClass.getPercGrowth());

        intimidate.setPointsFromClass(newClass.getIntimidatePerLevel() * lvl);
        intimidate.setDevPerPoint(newClass.getSocGrowth());
        leadership.setPointsFromClass(newClass.getLeaderPerLevel() * lvl);
        leadership.setDevPerPoint(newClass.getSocGrowth());
        persuasion.setPointsFromClass(newClass.getPersuadePerLevel() * lvl);
        persuasion.setDevPerPoint(newClass.getSocGrowth());
        style.setPointsFromClass(newClass.getStylePerLevel() * lvl);
        style.setDevPerPoint(newClass.getSocGrowth());

        disguise.setPointsFromClass(newClass.getDisguisePerLevel() * lvl);
        disguise.setDevPerPoint(newClass.getSubterGrowth());
        hide.setPointsFromClass(newClass.getHidePerLevel() * lvl);
        hide.setDevPerPoint(newClass.getSubterGrowth());
        lockPick.setPointsFromClass(newClass.getLockpickPerLevel() * lvl);
        lockPick.setDevPerPoint(newClass.getSubterGrowth());
        poisons.setPointsFromClass(newClass.getPoisonPerLevel() * lvl);
        poisons.setDevPerPoint(newClass.getSubterGrowth());
        theft.setPointsFromClass(newClass.getTheftPerLevel() * lvl);
        theft.setDevPerPoint(newClass.getSubterGrowth());
        stealth.setPointsFromClass(newClass.getStealthPerLevel() * lvl);
        stealth.setDevPerPoint(newClass.getSubterGrowth());
        trapLore.setPointsFromClass(newClass.getTrapPerLevel() * lvl);
        trapLore.setDevPerPoint(newClass.getSubterGrowth());

        animals.setPointsFromClass(newClass.getAnimalPerLevel() * lvl);
        animals.setDevPerPoint(newClass.getIntellGrowth());
        appraise.setPointsFromClass(newClass.getAppraisePerLevel() * lvl);
        appraise.setDevPerPoint(newClass.getIntellGrowth());
        herbalLore.setPointsFromClass(newClass.getHerbPerLevel() * lvl);
        herbalLore.setDevPerPoint(newClass.getIntellGrowth());
        history.setPointsFromClass(newClass.getHistPerLevel() * lvl);
        history.setDevPerPoint(newClass.getIntellGrowth());
        memorize.setPointsFromClass(newClass.getMemorizePerLevel() * lvl);
        memorize.setDevPerPoint(newClass.getIntellGrowth());
        magicAppraise.setPointsFromClass(newClass.getMagAppraisePerLevel() * lvl);
        magicAppraise.setDevPerPoint(newClass.getIntellGrowth());
        medic.setPointsFromClass(newClass.getMedicPerLevel() * lvl);
        medic.setDevPerPoint(newClass.getIntellGrowth());
        navigate.setPointsFromClass(newClass.getNavPerLevel() * lvl);
        navigate.setDevPerPoint(newClass.getIntellGrowth());
        occult.setPointsFromClass(newClass.getOccultPerLevel() * lvl);
        occult.setDevPerPoint(newClass.getIntellGrowth());
        sciences.setPointsFromClass(newClass.getSciencePerLevel() * lvl);
        sciences.setDevPerPoint(newClass.getIntellGrowth());

        composure.setPointsFromClass(newClass.getComposePerLevel() * lvl);
        composure.setDevPerPoint(newClass.getVigGrowth());
        strengthFeat.setPointsFromClass(newClass.getStrengthFeatPerLevel() * lvl);
        strengthFeat.setDevPerPoint(newClass.getVigGrowth());
        resistPain.setPointsFromClass(newClass.getStandPainPerLevel() * lvl);
        resistPain.setDevPerPoint(newClass.getVigGrowth());
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

    public void loadList(BufferedReader fileReader) throws IOException {
        acrobatics.load(fileReader, this);
        athletics.load(fileReader, this);
        climb.load(fileReader, this);
        jump.load(fileReader, this);
        ride.load(fileReader, this);
        swim.load(fileReader, this);

        art.load(fileReader, this);
        dance.load(fileReader, this);
        forging.load(fileReader, this);
        music.load(fileReader, this);
        sleightHand.load(fileReader, this);

        notice.load(fileReader, this);
        search.load(fileReader, this);
        track.load(fileReader, this);

        intimidate.load(fileReader, this);
        leadership.load(fileReader, this);
        persuasion.load(fileReader, this);
        style.load(fileReader, this);

        disguise.load(fileReader, this);
        hide.load(fileReader, this);
        lockPick.load(fileReader, this);
        poisons.load(fileReader, this);
        theft.load(fileReader, this);
        stealth.load(fileReader, this);
        trapLore.load(fileReader, this);

        animals.load(fileReader, this);
        appraise.load(fileReader, this);
        herbalLore.load(fileReader, this);
        history.load(fileReader, this);
        memorize.load(fileReader, this);
        magicAppraise.load(fileReader, this);
        medic.load(fileReader, this);
        navigate.load(fileReader, this);
        occult.load(fileReader, this);
        sciences.load(fileReader, this);

        composure.load(fileReader, this);
        strengthFeat.load(fileReader, this);
        resistPain.load(fileReader, this);
    }

    public void writeList(SerialOutputStream byteArray) {
        acrobatics.write(byteArray);
        athletics.write(byteArray);
        climb.write(byteArray);
        jump.write(byteArray);
        ride.write(byteArray);
        swim.write(byteArray);

        art.write(byteArray);
        dance.write(byteArray);
        forging.write(byteArray);
        music.write(byteArray);
        sleightHand.write(byteArray);

        notice.write(byteArray);
        search.write(byteArray);
        track.write(byteArray);

        intimidate.write(byteArray);
        leadership.write(byteArray);
        persuasion.write(byteArray);
        style.write(byteArray);

        disguise.write(byteArray);
        hide.write(byteArray);
        lockPick.write(byteArray);
        poisons.write(byteArray);
        theft.write(byteArray);
        stealth.write(byteArray);
        trapLore.write(byteArray);

        animals.write(byteArray);
        appraise.write(byteArray);
        herbalLore.write(byteArray);
        history.write(byteArray);
        memorize.write(byteArray);
        magicAppraise.write(byteArray);
        medic.write(byteArray);
        navigate.write(byteArray);
        occult.write(byteArray);
        sciences.write(byteArray);

        composure.write(byteArray);
        strengthFeat.write(byteArray);
        resistPain.write(byteArray);
    }
}

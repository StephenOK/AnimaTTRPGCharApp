package com.example.animabuilder.Activities.Fragments.SubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.animabuilder.CharacterCreation.Attributes.SecondaryCharacteristic;
import com.example.animabuilder.CharacterCreation.SecondaryInput;
import com.example.animabuilder.R;

import com.example.animabuilder.CharacterCreation.BaseCharacter;

public class SecondaryTable extends Fragment {
    BaseCharacter charInstance;
    int rID;

    public SecondaryTable(BaseCharacter charInstance, int rID){
        this.charInstance = charInstance;
        this.rID = rID;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(rID, container, false);

        TableLayout workingTable= view.findViewById(R.id.heldTable);

        for(int i = 1; i < workingTable.getChildCount(); i++){
            TableRow workingRow = (TableRow) workingTable.getChildAt(i);

            SecondaryCharacteristic workingStat;
            String rowText = ((TextView)workingRow.getChildAt(0)).getText().toString();

            switch(rowText){
                case "Acrobatics": workingStat = charInstance.getSecondaryList().getAcrobatics(); break;
                case "Athletics": workingStat = charInstance.getSecondaryList().getAthletics(); break;
                case "Climb": workingStat = charInstance.getSecondaryList().getClimb(); break;
                case "Jump": workingStat = charInstance.getSecondaryList().getJump(); break;
                case "Ride": workingStat = charInstance.getSecondaryList().getRide(); break;
                case "Swim": workingStat = charInstance.getSecondaryList().getSwim(); break;

                case "Intimidate": workingStat = charInstance.getSecondaryList().getIntimidate(); break;
                case "Leadership": workingStat = charInstance.getSecondaryList().getLeadership(); break;
                case "Persuasion": workingStat = charInstance.getSecondaryList().getPersuasion(); break;
                case "Style": workingStat = charInstance.getSecondaryList().getStyle(); break;

                case "Notice": workingStat = charInstance.getSecondaryList().getNotice(); break;
                case "Search": workingStat = charInstance.getSecondaryList().getSearch(); break;
                case "Track": workingStat = charInstance.getSecondaryList().getTrack(); break;

                case "Animals": workingStat = charInstance.getSecondaryList().getAnimals(); break;
                case "Appraise": workingStat = charInstance.getSecondaryList().getAppraise(); break;
                case "Herb Lore": workingStat = charInstance.getSecondaryList().getHerbalLore(); break;
                case "History": workingStat = charInstance.getSecondaryList().getHistory(); break;
                case "Memorize": workingStat = charInstance.getSecondaryList().getMemorize(); break;
                case "Magic Appraise": workingStat = charInstance.getSecondaryList().getMagicAppraise(); break;
                case "Medicine": workingStat = charInstance.getSecondaryList().getMedic(); break;
                case "Navigation": workingStat = charInstance.getSecondaryList().getNavigate(); break;
                case "Occult": workingStat = charInstance.getSecondaryList().getOccult(); break;
                case "Science": workingStat = charInstance.getSecondaryList().getSciences(); break;

                case "Composure": workingStat = charInstance.getSecondaryList().getComposure(); break;
                case "Feats of Strength": workingStat = charInstance.getSecondaryList().getStrengthFeat(); break;
                case "Withstand Pain": workingStat = charInstance.getSecondaryList().getResistPain(); break;

                case "Disguise": workingStat = charInstance.getSecondaryList().getDisguise(); break;
                case "Hide": workingStat = charInstance.getSecondaryList().getHide(); break;
                case "Lock Picking": workingStat = charInstance.getSecondaryList().getLockPick(); break;
                case "Poisons": workingStat = charInstance.getSecondaryList().getPoisons(); break;
                case "Theft": workingStat = charInstance.getSecondaryList().getTheft(); break;
                case "Stealth": workingStat = charInstance.getSecondaryList().getStealth(); break;
                case "Trap Lore": workingStat = charInstance.getSecondaryList().getTrapLore(); break;

                case "Art": workingStat = charInstance.getSecondaryList().getArt(); break;
                case "Dance": workingStat = charInstance.getSecondaryList().getDance(); break;
                case "Forging": workingStat = charInstance.getSecondaryList().getForging(); break;
                case "Music": workingStat = charInstance.getSecondaryList().getMusic(); break;
                case "Sleight of Hand": workingStat = charInstance.getSecondaryList().getSleightHand(); break;

                default: workingStat = new SecondaryCharacteristic(); break;
            }

            ((EditText)workingRow.getChildAt(1)).setText(getString(R.string.intItem, workingStat.getPointsApplied()));
            ((EditText)workingRow.getChildAt(1)).addTextChangedListener(new SecondaryInput(workingRow, view.getContext(), workingStat));

            ((TextView)workingRow.getChildAt(2)).setText(getString(R.string.intItem, workingStat.getModVal()));
            ((TextView)workingRow.getChildAt(3)).setText(getString(R.string.intItem, workingStat.getPointsFromClass()));
            if(workingStat.isBonusApplied())
                ((TextView)workingRow.getChildAt(4)).setText("5");
            else
                ((TextView)workingRow.getChildAt(4)).setText("0");
            ((TextView)workingRow.getChildAt(5)).setText(getString(R.string.intItem, workingStat.getTotal()));
        }

        return view;
    }
}

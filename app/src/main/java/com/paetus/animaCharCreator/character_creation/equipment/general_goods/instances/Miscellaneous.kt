package com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralCategory
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

class Miscellaneous: GeneralCategory(qualityInput = null) {
    private val oil = GeneralEquipment(
        saveName = "Oil",
        name = R.string.oil,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val torch = GeneralEquipment(
        saveName = "Torch",
        name = R.string.torch,
        baseCost = 2.0,
        coinType = CoinType.Copper,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val smallChest = GeneralEquipment(
        saveName = "Small Chest",
        name = R.string.smallChest,
        baseCost = 15.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val largeChest = GeneralEquipment(
        saveName = "Large Chest",
        name = R.string.largeChest,
        baseCost = 25.0,
        coinType = CoinType.Silver,
        weight = 10.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val harpoon = GeneralEquipment(
        saveName = "Harpoon",
        name = R.string.generalHarpoon,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val barrel = GeneralEquipment(
        saveName = "Barrel",
        name = R.string.barrel,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val cane = GeneralEquipment(
        saveName = "Cane",
        name = R.string.cane,
        baseCost = 2.0,
        coinType = CoinType.Copper,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val smallBag = GeneralEquipment(
        saveName = "Small Bag",
        name = R.string.smallBag,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    private val largeBag = GeneralEquipment(
        saveName = "Large Bag",
        name = R.string.largeBag,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val smallBottle = GeneralEquipment(
        saveName = "Small Glass Bottle",
        name = R.string.smallBottle,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val largeBottle = GeneralEquipment(
        saveName = "Large Glass Bottle",
        name = R.string.largeBottle,
        baseCost = 50.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val lightChain = GeneralEquipment(
        saveName = "Light Chain (1 meter)",
        name = R.string.lightChain,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val heavyChain = GeneralEquipment(
        saveName = "Heavy Chain (1 meter)",
        name = R.string.heavyChain,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val quiver = GeneralEquipment(
        saveName = "Quiver of Arrows",
        name = R.string.quiver,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val box = GeneralEquipment(
        saveName = "Box",
        name = R.string.box,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val bell = GeneralEquipment(
        saveName = "Bell",
        name = R.string.bell,
        baseCost = 25.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val spyglass = GeneralEquipment(
        saveName = "Spyglass",
        name = R.string.spyglass,
        baseCost = 250.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val smallTrap = GeneralEquipment(
        saveName = "Small Trap",
        name = R.string.smallTrap,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 3.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val largeTrap = GeneralEquipment(
        saveName = "Large Trap",
        name = R.string.largeTrap,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 5.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val decentLock = GeneralEquipment(
        saveName = "Decent Lock",
        name = R.string.decentLock,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodLock = GeneralEquipment(
        saveName = "Good Lock",
        name = R.string.goodLock,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    val excellentLock = GeneralEquipment(
        saveName = "Excellent Lock",
        name = R.string.excellentLock,
        baseCost = 80.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val smallBasket = GeneralEquipment(
        saveName = "Small Basket",
        name = R.string.smallBasket,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val largeBasket = GeneralEquipment(
        saveName = "Large Basket",
        name = R.string.largeBasket,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val cross = GeneralEquipment(
        saveName = "Cross",
        name = R.string.cross,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val bucket = GeneralEquipment(
        saveName = "Bucket",
        name = R.string.bucket,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val decentRope = GeneralEquipment(
        saveName = "Decent Rope (10 meters)",
        name = R.string.decentRope,
        baseCost = 5.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodRope = GeneralEquipment(
        saveName = "Good Rope (10 meters)",
        name = R.string.goodRope,
        baseCost = 25.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val excellentRope = GeneralEquipment(
        saveName = "Excellent Rope (10 meters)",
        name = R.string.excellentRope,
        baseCost = 5.0,
        coinType = CoinType.Gold,
        weight = 6.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val ropeLadder = GeneralEquipment(
        saveName = "Rope Ladder (10 meters)",
        name = R.string.ropeLadder,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = 6.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val metalMirror = GeneralEquipment(
        saveName = "Small Metal Mirror",
        name = R.string.smallMetalMirror,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val glassMirror = GeneralEquipment(
        saveName = "Small Glass Mirror",
        name = R.string.smallGlassMirror,
        baseCost = 200.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val spectacles = GeneralEquipment(
        saveName = "Spectacles",
        name = R.string.spectacles,
        baseCost = 180.0,
        coinType = CoinType.Gold,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val lockPick = GeneralEquipment(
        saveName = "Lock Pick",
        name = R.string.lockpick,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val hook = GeneralEquipment(
        saveName = "Hook",
        name = R.string.generalHook,
        baseCost = 15.0,
        coinType = CoinType.Silver,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val soap = GeneralEquipment(
        saveName = "Soap",
        name = R.string.soap,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val smallNotebook = GeneralEquipment(
        saveName = "Small Blank-paged Book",
        name = R.string.smallBook,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val largeNotebook = GeneralEquipment(
        saveName = "Large Blank-paged Book",
        name = R.string.largeBook,
        baseCost = 100.0,
        coinType = CoinType.Gold,
        weight = 2.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val limeStick = GeneralEquipment(
        saveName = "Lime Stick",
        name = R.string.limeStick,
        baseCost = 25.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val candleLantern = GeneralEquipment(
        saveName = "Lantern for Candles",
        name = R.string.candleLantern,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = 0.25,
        availability = Availability.Common,
        currentQuality = null
    )

    private val oilLamp = GeneralEquipment(
        saveName = "Oil Lamp",
        name = R.string.oilLamp,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = 2.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val canvas = GeneralEquipment(
        saveName = "Canvas (1 square meter)",
        name = R.string.canvas,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val blanket = GeneralEquipment(
        saveName = "Blanket",
        name = R.string.blanket,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val backpack = GeneralEquipment(
        saveName = "Backpack",
        name = R.string.backpack,
        baseCost = 30.0,
        coinType = CoinType.Silver,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val monocle = GeneralEquipment(
        saveName = "Monocle",
        name = R.string.monocle,
        baseCost = 200.0,
        coinType = CoinType.Gold,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val wineskin = GeneralEquipment(
        saveName = "Wineskin",
        name = R.string.wineskin,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val paper = GeneralEquipment(
        saveName = "Paper",
        name = R.string.paper,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = null,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val papyrus = GeneralEquipment(
        saveName = "Papyrus",
        name = R.string.papyrus,
        baseCost = 20.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val decentPerfume = GeneralEquipment(
        saveName = "Decent Perfume",
        name = R.string.decentPerfume,
        baseCost = 35.0,
        coinType = CoinType.Silver,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val goodPerfume = GeneralEquipment(
        saveName = "Good Perfume",
        name = R.string.goodPerfume,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val excellentPerfume = GeneralEquipment(
        saveName = "Excellent Perfume",
        name = R.string.excellentPerfume,
        baseCost = 100.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val parchment = GeneralEquipment(
        saveName = "Parchment",
        name = R.string.parchment,
        baseCost = 50.0,
        coinType = CoinType.Silver,
        weight = null,
        availability = Availability.Common,
        currentQuality = null
    )

    private val whetstone = GeneralEquipment(
        saveName = "Sharpening Stone",
        name = R.string.whetstone,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = 0.3,
        availability = Availability.Common,
        currentQuality = null
    )

    private val fishingNet = GeneralEquipment(
        saveName = "Fishing Net (1 meter)",
        name = R.string.fishingNet,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = 0.5,
        availability = Availability.Common,
        currentQuality = null
    )

    private val hourglass = GeneralEquipment(
        saveName = "Hourglass",
        name = R.string.hourglass,
        baseCost = 2.0,
        coinType = CoinType.Gold,
        weight = 0.5,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val mechClock = GeneralEquipment(
        saveName = "Mechanical Clock",
        name = R.string.mechClock,
        baseCost = 300.0,
        coinType = CoinType.Gold,
        weight = 20.0,
        availability = Availability.Rare,
        currentQuality = null
    )

    private val smallSack = GeneralEquipment(
        saveName = "Small Sack",
        name = R.string.smallSack,
        baseCost = 2.0,
        coinType = CoinType.Silver,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val largeSack = GeneralEquipment(
        saveName = "large Sack",
        name = R.string.largeSack,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.4,
        availability = Availability.Common,
        currentQuality = null
    )

    private val whistle = GeneralEquipment(
        saveName = "Whistle",
        name = R.string.whistle,
        baseCost = 10.0,
        coinType = CoinType.Silver,
        weight = 0.1,
        availability = Availability.Common,
        currentQuality = null
    )

    private val smallTent = GeneralEquipment(
        saveName = "Small Tent",
        name = R.string.smallTent,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 1.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val largeTent = GeneralEquipment(
        saveName = "Large Tent",
        name = R.string.largeTent,
        baseCost = 20.0,
        coinType = CoinType.Gold,
        weight = 6.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val pavilionTent = GeneralEquipment(
        saveName = "Pavilion Tent",
        name = R.string.pavilionTent,
        baseCost = 150.0,
        coinType = CoinType.Gold,
        weight = 14.0,
        availability = Availability.Common,
        currentQuality = null
    )

    private val luxuryTent = GeneralEquipment(
        saveName = "Luxurious Tent",
        name = R.string.luxuryTent,
        baseCost = 200.0,
        coinType = CoinType.Gold,
        weight = 4.0,
        availability = Availability.Uncommon,
        currentQuality = null
    )

    private val ink = GeneralEquipment(
        saveName = "Ink",
        name = R.string.ink,
        baseCost = 1.0,
        coinType = CoinType.Gold,
        weight = 0.3,
        availability = Availability.Common,
        currentQuality = null
    )

    private val chalk = GeneralEquipment(
        saveName = "Chalk",
        name = R.string.chalk,
        baseCost = 5.0,
        coinType = CoinType.Copper,
        weight = 0.2,
        availability = Availability.Common,
        currentQuality = null
    )

    private val flintAndTinder = GeneralEquipment(
        saveName = "Flint and Tinder",
        name = R.string.flintTinder,
        baseCost = 1.0,
        coinType = CoinType.Silver,
        weight = 0.3,
        availability = Availability.Common,
        currentQuality = null
    )

    init{
        itemsAvailable.addAll(listOf(
            oil,
            torch,
            smallChest,
            largeChest,
            harpoon,
            barrel,
            cane,
            smallBag,
            largeBag,
            smallBottle,
            largeBottle,
            lightChain,
            heavyChain,
            quiver,
            box,
            bell,
            spyglass,
            smallTrap,
            largeTrap,
            decentLock,
            goodLock,
            excellentLock,
            smallBasket,
            largeBasket,
            cross,
            bucket,
            decentRope,
            goodRope,
            excellentRope,
            ropeLadder,
            metalMirror,
            glassMirror,
            spectacles,
            lockPick,
            hook,
            soap,
            smallNotebook,
            largeNotebook,
            limeStick,
            candleLantern,
            oilLamp,
            canvas,
            blanket,
            backpack,
            monocle,
            wineskin,
            paper,
            papyrus,
            decentPerfume,
            goodPerfume,
            excellentPerfume,
            parchment,
            whetstone,
            fishingNet,
            hourglass,
            mechClock,
            smallSack,
            largeSack,
            whistle,
            smallTent,
            largeTent,
            pavilionTent,
            luxuryTent,
            ink,
            chalk,
            flintAndTinder
        ))
    }
}
/* ------------------------------------------------------------------------------------------------------------
• FoscStartBeam (abjad 3.0)

------------------------------------------------------------------------------------------------------------ */
FoscStartBeam : Fosc {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    var <direction, <tweaks;
    var <context='Voice', <parameter='BEAM', <persistent=true, <publishStorageFormat=true;
    *new { |direction, tweaks|
        if (direction.notNil) { direction = direction.toTridirectionalLilypondSymbol };
        ^super.new.init(direction, tweaks);
    }
    init { |argDirection, argTweaks|
        direction = argDirection;
        FoscLilypondTweakManager.setTweaks(this, argTweaks);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC INSTANCE PROPERTIES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • context

    Gets context. Returns 'Voice'.
    

    • Example 1

    a = FoscStartBeam();
    a.context;
    -------------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------
    • direction

    Gets direction.


    • Example 1

    a = FoscStartBeam();
    a.direction;
    -------------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------
    • parameter

    Gets parameter. Returns 'Beam'.


    • Example 1

    a = FoscStartBeam();
    a.parameter;
    -------------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------
    • persistent

    Is true.


    • Example 1

    a = FoscStartBeam();
    a.persistent;
    -------------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------
    • spannerStart

    Is true.


    • Example 1

    a = FoscStartBeam();
    a.spannerStart;
    -------------------------------------------------------------------------------------------------------- */
    spannerStart {
        ^true;
    }
    /* --------------------------------------------------------------------------------------------------------
    • tweaks

    Gets tweaks.


    • Example 1

    a = FoscStartBeam();
    a.tweaks;
    -------------------------------------------------------------------------------------------------------- */
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC INSTANCE METHODS: SPECIAL METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • ==

    !!!TODO

    Is true when all initialization values of Abjad value object equal the initialization values of 'argument'.
    
    def __eq__(self, argument) -> bool:
        return StorageFormatManager.compare_objects(self, argument)
    -------------------------------------------------------------------------------------------------------- */
    == { |object|
        if (object.isKindOf(FoscStartBeam).not) { ^false };
        ^true;
    }
    /* --------------------------------------------------------------------------------------------------------
    • asCompileString

    !!!TODO: not yet implemented

    Gets interpreter representation.
    -------------------------------------------------------------------------------------------------------- */
    // asCompileString {
    //     ^this.notYetImplemented(thisMethod);
    // }
    /* --------------------------------------------------------------------------------------------------------
    • hash

    !!!TODO: not yet implemented

    Hashes Abjad value object.
    -------------------------------------------------------------------------------------------------------- */
    // hash {
    //     ^this.notYetImplemented(thisMethod);
    // }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE INSTANCE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • prAddDirection
    -------------------------------------------------------------------------------------------------------- */
    prAddDirection { |string|
        if (direction.notNil) {
            string = "% %".format(direction, string);
        };
        ^string;
    }
    /* --------------------------------------------------------------------------------------------------------
    • prGetLilypondFormatBundle
    -------------------------------------------------------------------------------------------------------- */
    prGetLilypondFormatBundle { |component|
        var bundle, localTweaks, string;
        bundle = FoscLilypondFormatBundle();
        if (tweaks.notNil) {
            localTweaks = tweaks.prListFormatContributions;
            bundle.after.spannerStarts.addAll(localTweaks);
        };
        string = this.prAddDirection("[");
        bundle.after.spannerStarts.add(string);
        ^bundle;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE CLASS METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • *prIsBeamable
    -------------------------------------------------------------------------------------------------------- */
    *prIsBeamable { |leaf, beamRests=false|
        var prototype;
        if ([FoscChord, FoscNote].any { |type| leaf.isKindOf(type) }) {
            if (0 < leaf.writtenDuration.flagCount) { ^true };
        };
        prototype = [FoscMultimeasureRest, FoscRest, FoscSkip];
        if (beamRests && { prototype.any { |type| leaf.isKindOf(type) } }) {
            ^true;
        };
        ^false;
    }
}

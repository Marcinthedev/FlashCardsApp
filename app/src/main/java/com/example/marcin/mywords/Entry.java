package com.example.marcin.mywords;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

    @SerializedName("homographNumber")
    @Expose
    private String homographNumber;
    @SerializedName("senses")
    @Expose
    private List<Sense> senses = null;

    public String getHomographNumber() {
        return homographNumber;
    }

    public void setHomographNumber(String homographNumber) {
        this.homographNumber = homographNumber;
    }

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(List<Sense> senses) {
        this.senses = senses;
    }

}
/*-----------------------------------com.example.Example.java-----------------------------------

        package com.example;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;*/

/*-----------------------------------com.example.LexicalEntry.java-----------------------------------

        package com.example;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;*/

/*-----------------------------------com.example.Metadata.java-----------------------------------

        package com.example;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;*/

/*-----------------------------------com.example.Result.java-----------------------------------

        package com.example;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;*/

/*-----------------------------------com.example.Sense.java-----------------------------------

        package com.example;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;*/

/*-----------------------------------com.example.Subsense.java-----------------------------------

        package com.example;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;*/


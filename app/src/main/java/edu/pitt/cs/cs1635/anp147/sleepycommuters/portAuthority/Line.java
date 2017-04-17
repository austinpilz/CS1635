package edu.pitt.cs.cs1635.anp147.sleepycommuters.portAuthority;

/**
 * Created by austinpilz on 4/17/17.
 */

public class Line {

    private String lineNumber, lineLetter, lineDescription;

    public Line()
    {
        //
    }

    public Line(String ln, String ll, String ld)
    {
        this.lineNumber = ln;
        this.lineLetter = ll;
        this.lineDescription = ld;
    }

    public String getLineNumber()
    {
        return this.lineNumber;
    }

    public String getLineLetter()
    {
        return this.lineLetter;
    }

    public String getLineDescription()
    {
        return this.lineDescription;
    }

    public void setLineNumber(String val)
    {
        this.lineNumber = val;
    }

    public void setLineLetter(String val)
    {
        this.lineLetter = val;
    }

    public void setLineDescription(String val)
    {
        this.lineDescription = val;
    }

}

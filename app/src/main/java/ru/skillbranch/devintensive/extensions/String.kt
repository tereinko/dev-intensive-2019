package ru.skillbranch.devintensive.extensions

fun String.truncate(len: Int = 16): String {
    var s = this.trimStart().trimEnd()
    if (s.length <= len)
        return s
    s = s.substring(0, len)
    return s.trimEnd() + "..."
}

fun String.stripHtml(): String {
    var s = this
        .replace(
            Regex("""
                <[^<]*?>|&\\d+;
                |&quot;|&amp;|&lt;|&gt;|&OElig;|&oelig;|&Scaron;|&scaron;|&Yuml;|&circ;|&tilde;|&ensp;|&emsp;|&thinsp;|&zwnj;|&zwj;|&lrm;|&rlm;|&ndash;|&mdash;|&lsquo;|&rsquo;|&sbquo;|&ldquo;|&rdquo;|&bdquo;|&dagger;|&Dagger;|&permil;|&lsaquo;|&rsaquo;|&euro;
                |&#34;|&#38;|&#60;|&#62;|&#338;|&#339;|&#352;|&#353;|&#376;|&#710;|&#732;|&#8194;|&#8195;|&#8201;|&#8204;|&#8205;|&#8206;|&#8207;|&#8211;|&#8212;|&#8216;|&#8217;|&#8218;|&#8220;|&#8221;|&#8222;|&#8224;|&#8225;|&#8240;|&#8249;|&#8250;|&#8364;
                |&#x22;|&#x26;|&#x3C;|&#x3E;|&#x152;|&#x153;|&#x160;|&#x161;|&#x178;|&#x2C6;|&#x2DC;|&#x2002;|&#x2003;|&#x2009;|&#x200C;|&#x200D;|&#x200E;|&#x200F;|&#x2013;|&#x2014;|&#x2018;|&#x2019;|&#x201A;|&#x201C;|&#x201D;|&#x201E;|&#x2020;|&#x2021;|&#x2030;|&#x2039;|&#x203A;|&#x20AC;
                """.trimIndent()),
            ""
        )
        .replace(Regex("\\s+"), " ")


    return s
}
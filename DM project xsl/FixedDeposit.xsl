<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:exsl="http://exslt.org/common"
xmlns:math="http://exslt.org/math"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
extension-element-prefixes="exsl math">
<xsl:output method="text" encoding="UTF-8"/>


<xsl:template match="/">
     
   
    <xsl:variable name="amount" select="(FixedDeposit/principal*math:power((number(1+FixedDeposit/rate div 100)),(number(FixedDeposit/years))))"/>
    <xsl:text>Your maturity amount after </xsl:text>
	<xsl:value-of select="FixedDeposit/years"></xsl:value-of>
	<xsl:text> years is Rs </xsl:text>
	<xsl:value-of select="floor($amount)"></xsl:value-of>
 </xsl:template>

</xsl:stylesheet>

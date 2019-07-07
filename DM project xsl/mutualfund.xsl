<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:exsl="http://exslt.org/common"
xmlns:math="http://exslt.org/math"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
extension-element-prefixes="exsl math">
<xsl:template match="MutualFund">
	<xsl:choose>
     <xsl:when test="UserDetails/risk = 'High'">
     	<xsl:text>Since you are an AGGRESSIVE investor, you should invest in Equity Funds. You can spend an approximate amount of Rs. </xsl:text>
		<xsl:value-of select="number(UserDetails/savings) * (100-number(UserDetails/age)) div 100"/>
		<xsl:text>. A possible Fund in which you could invest (based on historic rates of return) is </xsl:text>
		<xsl:value-of select="Equity/fundname" />
		<xsl:text> with a return rate of </xsl:text>
		<xsl:value-of select="Equity/rate"/> 
		<xsl:text>%.</xsl:text>
     </xsl:when>
     <xsl:when test="UserDetails/risk = 'Moderate'">
     	<xsl:text>Since you are a MODERATE level investor, you should invest in Hybrid Funds. You can spend an approximate amount of Rs. </xsl:text>
		<xsl:value-of select="number(UserDetails/savings) * (100-number(UserDetails/age)) div 100"/>
		<xsl:text>. A possible Fund in which you could invest (based on historic rates of return) is </xsl:text>
		<xsl:value-of select="Hybrid/fundname" />
		<xsl:text> with a return rate of </xsl:text>
		<xsl:value-of select="Hybrid/rate"/> 
		<xsl:text>%.</xsl:text>
     </xsl:when>
     <xsl:when test="UserDetails/risk = 'Low'">
     	<xsl:text>Since you are an CONSERVATIVE investor, you should invest in Debt Funds. You can spend an approximate amount of Rs. </xsl:text>
		<xsl:value-of select="number(UserDetails/savings) * (100-number(UserDetails/age)) div 100"/>
		<xsl:text>. A possible Fund in which you could invest (based on historic rates of return) is </xsl:text>
		<xsl:value-of select="Debt/fundname" />
		<xsl:text> with a return rate of </xsl:text>
		<xsl:value-of select="Debt/rate"/> 
		<xsl:text>%.</xsl:text>
     </xsl:when>
 </xsl:choose>
 </xsl:template>
</xsl:stylesheet>    
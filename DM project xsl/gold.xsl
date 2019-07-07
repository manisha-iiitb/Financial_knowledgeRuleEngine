<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/Gold_investment">
	<xsl:variable name="amount" select="number(curr_rate) div number(grams)"/>
		<xsl:text>You will have to invest Rs. </xsl:text>
		<xsl:value-of select="number(current_rate) * number(grams)"/>
		<xsl:text> for </xsl:text>
		<xsl:value-of select="grams" />
		<xsl:text> grams of </xsl:text>
		<xsl:value-of select="carat"/> 
		<xsl:text> gold.</xsl:text>
	</xsl:template>
</xsl:stylesheet>

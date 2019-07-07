<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/stock">
	<xsl:variable name="ratio" select="Current_Stock_Price div EPS"/>
		<xsl:choose>
			<xsl:when test='$ratio &gt;14.0'>
					<xsl:text>You can expect higher growth in future for </xsl:text>
					<xsl:value-of select="company_name"/>
					<xsl:text> .</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>Higher growth in future for </xsl:text>
				<xsl:value-of select="company_name"></xsl:value-of>
				<xsl:text> is not expected.</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>

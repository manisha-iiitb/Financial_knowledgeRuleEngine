<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" encoding="UTF-8"/>
<xsl:template match="/">
<xsl:variable name="newline">
<xsl:text>
</xsl:text>
</xsl:variable>
<xsl:text>Gaining Stocks:# </xsl:text>

 <xsl:for-each select="SellStock/Company[LastPrice]">
   <xsl:if test="LastPrice &gt; Price ">
      <xsl:text>* Company name: </xsl:text>
        <xsl:value-of select="cname"/>
       <xsl:text> Last Price: </xsl:text>
        <xsl:value-of select="LastPrice"/>
      <xsl:text>,  Change in Company: </xsl:text>
        <xsl:value-of select="ChangeInCompany"/>
      <xsl:text>,  Change Percentage: </xsl:text>
       <xsl:value-of select="ChangePercent"/>
      <xsl:text>%,  Profit: Rs </xsl:text>
       <xsl:value-of select="Profit"/>
      <xsl:text>,  Liabililty: Rs </xsl:text>
       <xsl:value-of select="Liability"/>
      <xsl:text>,  ProfitLiabililtyRatio: </xsl:text>
       <xsl:value-of select="ProfitLiabilityRatio"/>
       <xsl:text>#</xsl:text>
    </xsl:if>
 </xsl:for-each>

 <xsl:text> Losing Stocks:# </xsl:text>
 
 <xsl:for-each select="SellStock/Company[LastPrice]">
   <xsl:if test="LastPrice &lt; Price ">
        <xsl:text> Company name: </xsl:text>
      <xsl:value-of select="cname"/>
       <xsl:text> Last Price: </xsl:text>
      <xsl:value-of select="LastPrice"/>
        <xsl:text>,  Change in Company: </xsl:text>
      <xsl:value-of select="ChangeInCompany"/>
        <xsl:text>,  Change Percentage: </xsl:text>
      <xsl:value-of select="ChangePercent"/>
        <xsl:text>%,  Profit: Rs</xsl:text>
      <xsl:value-of select="Profit"/>
        <xsl:text>,  Liabililty: Rs</xsl:text>
       <xsl:value-of select="Liability"/>
         <xsl:text>,  ProfitLiabililtyRatio: </xsl:text>
       <xsl:value-of select="ProfitLiabilityRatio"/>
       <xsl:text>#</xsl:text>
   </xsl:if>
 </xsl:for-each>
</xsl:template>
</xsl:stylesheet>

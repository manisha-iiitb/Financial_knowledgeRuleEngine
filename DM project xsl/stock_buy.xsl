<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text" encoding="UTF-8"/>
<xsl:template match="/">
  <xsl:variable name="abc">
      <xsl:value-of select="Buy/User/InvestmentType"/>
  </xsl:variable>
     <xsl:if test="$abc = 'Conservative'">
       <xsl:text>#Company name: </xsl:text>
       <xsl:value-of select="Buy/Conservative/CompanyName"/>
       <xsl:text>#Last Price: Rs </xsl:text>
              <xsl:value-of select="Buy/Aggressive/LastPrice"/>
       <xsl:text>#Change in Company: </xsl:text>
       <xsl:value-of select="Buy/Conservative/ChangeInCompany"/>
  <xsl:text>#Units to Buy: </xsl:text>
  <xsl:variable name="unit">
       <xsl:value-of select="Buy/User/Amount div Buy/Conservative/LastPrice"/>
       </xsl:variable>
       <xsl:value-of select="floor($unit)"/>
  <xsl:text>#Change Percentage: </xsl:text>
       <xsl:value-of select="Buy/Conservative/ChangePercent"/>
  <xsl:text>%#Profit: Rs </xsl:text>
       <xsl:value-of select="Buy/Conservative/Profit"/>
  <xsl:text>#Liabililty: Rs </xsl:text>
       <xsl:value-of select="Buy/Conservative/Liability"/>

     </xsl:if>
     <xsl:if test="$abc = 'Aggressive'">
       <xsl:text>#Company name: </xsl:text>
       <xsl:value-of select="Buy/Aggressive/CompanyName"/>
       <xsl:text>#Last Price: Rs </xsl:text>
              <xsl:value-of select="Buy/Aggressive/LastPrice"/>
       <xsl:text>#Change in company: </xsl:text>
       <xsl:value-of select="Buy/Aggressive/ChangeInCompany"/>
       <xsl:text>#Units to buy: </xsl:text>
       <xsl:variable name="unit">
       <xsl:value-of select="Buy/User/Amount div Buy/Aggressive/LastPrice"/>
       </xsl:variable>
       <xsl:value-of select="floor($unit)"/>    
  <xsl:text>#Change Percentage: </xsl:text>
       <xsl:value-of select="Buy/Aggressive/ChangePercent"/>
  <xsl:text>%#Profit: Rs </xsl:text>
       <xsl:value-of select="Buy/Aggressive/Profit"/>
  <xsl:text>#Liabililty: Rs </xsl:text>
       <xsl:value-of select="Buy/Aggressive/Liability"/>
     </xsl:if>
</xsl:template>
</xsl:stylesheet>

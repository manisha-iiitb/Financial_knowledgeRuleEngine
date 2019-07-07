<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:exsl="http://exslt.org/common"
xmlns:math="http://exslt.org/math"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
extension-element-prefixes="exsl math">
<xsl:output method="text" encoding="UTF-8"/>

<xsl:template match="/">
  
       <xsl:text>Your monthly EMI is </xsl:text>
        <xsl:value-of select="HomeLoan/principal * (HomeLoan/rateofInterest div 1200) * math:power((1+HomeLoan/rateofInterest div 1200),number(HomeLoan/timePeriod)) div math:power(1+(HomeLoan/rateofInterest div 1200),number((HomeLoan/timePeriod)-1))"/>	
       <!-- <output>   
      <xsl:value-of select="math:power(HomeLoan/rate, 2.4)" />
</output>-->
  
</xsl:template>
</xsl:stylesheet>



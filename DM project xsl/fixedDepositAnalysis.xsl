<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:exsl="http://exslt.org/common"
xmlns:math="http://exslt.org/math"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
extension-element-prefixes="exsl math">
<xsl:output method="text" encoding="UTF-8"/>

<!--1/(1+e^(-t))--><!-- this is required formula -->

<xsl:template match="/">
  
 <xsl:choose>
    <xsl:when test="FixedDeposit/category = 'senior'">
      <xsl:choose>
         <xsl:when test="FixedDeposit/income &lt; 250000">
            <xsl:text>You can open FD in </xsl:text>
             <xsl:value-of select="FixedDeposit/Senior-Citizen/Bank-NormalFD"/>
             <xsl:text> which provide heighest rate of interest for senior citizen of rate </xsl:text>
              <xsl:value-of select="FixedDeposit/Senior-Citizen/NormalFDRate"/>
               <xsl:text>%.Since your total income for the year is below Rs 2.5 lakh, you can submit or use form 15H. This will ensure that the bank does  not deduct TDS since income does not fall in the taxable slabs and you are not liable to pay any taxes.
            </xsl:text>
         </xsl:when>
         
         <xsl:otherwise>
              <xsl:text>Since your income fall in taxable slab ,</xsl:text>
	     <xsl:if test="FixedDeposit/years &gt;= 5 and FixedDeposit/amount &gt;= 1000">
                <xsl:text> you can choose Tax-Saving Fixed Deposits in which you can claim a deduction of up to a maximum of Rs 1,50,000 by investing in them. Currently </xsl:text>
                 <xsl:value-of select="FixedDeposit/Senior-Citizen/Bank-TaxSavingFD"/>
                 <xsl:text> is providing heighest tax-saving rate of interest for senior citizen of rate </xsl:text>
		 <xsl:value-of select="FixedDeposit/Senior-Citizen/TaxSavingFDRate"/>
                 <xsl:text>%.For further details on Tax-Saving Fixed Deposit you can visit this website- https://www.paisabazaar.com/fixed-deposit/tax-exemption-on-fixed-deposit/ . Otherwise,</xsl:text> 	     
	  </xsl:if>
                <xsl:text>You can open FD in </xsl:text>
             <xsl:value-of select="FixedDeposit/Senior-Citizen/Bank-NormalFD"/>
             <xsl:text> which currently providing heighest rate of interest for senior citizen of rate </xsl:text>
              <xsl:value-of select="FixedDeposit/Senior-Citizen/NormalFDRate"/><xsl:text>.</xsl:text>
          </xsl:otherwise>
              
        </xsl:choose>
     
    </xsl:when>

  <xsl:otherwise>

   <xsl:choose>
         <xsl:when test="FixedDeposit/income &lt; 250000">
            <xsl:text>You can open FD in </xsl:text>
             <xsl:value-of select="FixedDeposit/Regular-Citizen/Bank-NormalFD"/>
             <xsl:text> which provide heighest rate of interest for regular citizen of rate </xsl:text>
              <xsl:value-of select="FixedDeposit/Regular-Citizen/NormalFDRate"/>
               <xsl:text>%.Since your total income for the year is below Rs 2.5 lakh, you can submit or use form 15G. This will ensure that the bank does  not deduct TDS since income does not fall in the taxable slabs and you are not liable to pay any taxes.
            </xsl:text>
         </xsl:when>
         
         <xsl:otherwise>
              <xsl:text>Since your income fall in taxable slab ,</xsl:text>
	     <xsl:if test="FixedDeposit/years &gt;= 5 and FixedDeposit/amount &gt;= 1000">
                <xsl:text> you can choose Tax-Saving Fixed Deposits in which you can claim a deduction of up to a maximum of Rs 1,50,000 by investing in them. Currently </xsl:text>
                 <xsl:value-of select="FixedDeposit/Regular-Citizen/Bank-TaxSavingFD"/>
                 <xsl:text> is providing heighest tax-saving rate of interest for regular citizen of </xsl:text>
		 <xsl:value-of select="FixedDeposit/Regular-Citizen/TaxSavingFDRate"/>
                 <xsl:text>. For further details on Tax-Saving Fixed Deposit you can visit this website- https://www.paisabazaar.com/fixed-deposit/tax-exemption-on-fixed-deposit. Otherwise, </xsl:text> 	     
	  </xsl:if>
                <xsl:text>You can open FD in </xsl:text>
             <xsl:value-of select="FixedDeposit/Regular-Citizen/Bank-NormalFD"/>
             <xsl:text> which currently providing heighest rate of interest for regular citizen of rate </xsl:text>
              <xsl:value-of select="FixedDeposit/Regular-Citizen/NormalFDRate"/><xsl:text>.</xsl:text>
          </xsl:otherwise>
              
        </xsl:choose>
   
      
   </xsl:otherwise>
</xsl:choose>

</xsl:template>

</xsl:stylesheet>



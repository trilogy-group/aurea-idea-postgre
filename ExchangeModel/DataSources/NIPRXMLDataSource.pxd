<?xml version='1.0' encoding='UTF-8'?>
<XmlDatasourceModel xmlns='http://www.pantero.com/metamodel2' isSynthetic='false' name='com.nipr.NIPRXMLDataSource' schemaName='com.nipr.NIPRWrapperSchema'>
  <ModelMap isSynthetic='false'>
  </ModelMap>
  <XmlDatasourceOperation isSynthetic='false' name='GetIndividualNPN' action='OnInsert'>
    <OperationMethod isSynthetic='false' name='GetIndividualNPN' returnTypeName='com.nipr.info.HITLIST' language='JAVA' returnTypeElementName='GetIndividualNPNReturn'>
      <ReturnTypeDescription></ReturnTypeDescription>
      <Code></Code>
      <Parameter isSynthetic='false' name='Parameter1' typeName='com.nipr.Producer' unbindable='false'>
      </Parameter>
    </OperationMethod>
  </XmlDatasourceOperation>
  <XmlDatasourceOperation isSynthetic='false' name='GetFirmNPN' action='OnInsert'>
    <OperationMethod isSynthetic='false' name='GetFirmNPN' returnTypeName='com.nipr.info.HITLIST' language='JAVA' returnTypeElementName='GetFirmNPNReturn'>
      <ReturnTypeDescription></ReturnTypeDescription>
      <Code></Code>
      <Parameter isSynthetic='false' name='Parameter1' typeName='com.nipr.Producer' unbindable='false'>
      </Parameter>
    </OperationMethod>
  </XmlDatasourceOperation>
  <XmlDatasourceOperation isSynthetic='false' name='GetFirmInformation' action='OnInsert'>
    <OperationMethod isSynthetic='false' name='GetFirmInformation' returnTypeName='com.nipr.info.PDB' language='JAVA' returnTypeElementName='GetFirmInformationReturn'>
      <ReturnTypeDescription></ReturnTypeDescription>
      <Code></Code>
      <Parameter isSynthetic='false' name='Parameter1' typeName='com.nipr.ProducerInfo' unbindable='false'>
      </Parameter>
    </OperationMethod>
  </XmlDatasourceOperation>
  <XmlDatasourceOperation isSynthetic='false' name='GetIndividualInformation' action='OnInsert'>
    <OperationMethod isSynthetic='false' name='GetIndividualInformation' returnTypeName='com.nipr.info.PDB' language='JAVA' returnTypeElementName='GetIndividualInformationReturn'>
      <ReturnTypeDescription></ReturnTypeDescription>
      <Code></Code>
      <Parameter isSynthetic='false' name='Parameter1' typeName='com.nipr.ProducerInfo' unbindable='false'>
      </Parameter>
    </OperationMethod>
  </XmlDatasourceOperation>
</XmlDatasourceModel>

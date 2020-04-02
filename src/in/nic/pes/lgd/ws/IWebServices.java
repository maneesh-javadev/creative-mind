package in.nic.pes.lgd.ws;

/**
 * 
 * @author Sarvapriya Malhotra   
 *         
 *
 */
public interface IWebServices { // NO_UCD (unused code)
	
	/**
	 * 
	 * @param stateCode     -     Specific State for which Nomenclature is to be provided
	 * @param category      -     Specific Category within the State, possible values are
	 *                            U = URBAN
	 *                            R = RURAL
	 *                            T = TRADITIONAL
	 *                            A = ALL
	 * @param languageType  -     Specific languageType in which details are requested 
	 *                            E = English
                                  L = Local
                                  A = All (Both)  
	 * @return              -     List of Nomenclatures of Local Body Types for the requested State & Category
	 * @throws NotFoundException 
	 */
	NomenclatureOfLBTypes getLBTypes(int stateCode, Category category, LanguageType languageType) throws NotFoundException;
	
	/**
	 * 
	 * @param stateType     -     StateType can be either 
	 *                            A = ALL 
	 *                            S = STATE 
     *                            U = UT
	 * @return              -     List of ALL States for chosen StateType
	 * @throws NotFoundException
	 */
	States getListOfStates(StateType stateType) throws NotFoundException;
	
	/**
	 * 
	 * @return              -     List of All Districts of All States
	 * @throws NotFoundException
	 */
	States getListOfDistrcits() throws NotFoundException;
	
	/**
	 * 
	 * @param stateCode     -     StateCode for which Districts List is to be fetched
	 * @return              -     List of All Districts in the given state
	 * @throws NotFoundException
	 */
	State getListOfDistricts(int stateCode) throws NotFoundException;
	
	/**
	 * 
	 * @return              -     List of All SubDistricts of All States
	 * @throws NotFoundException
	 */
	Subdistricts getListOfSubDistricts() throws NotFoundException;
	
	/**
	 * 
	 * @param parentType    -     ParentType for which SubDistricts List is to be fetched possible values are:
	 *                            S = State
	 *                            D = District
	 * @param parentCode    -     ParentCode for which SubDistricts List is to be fetched
	 * @return              -     List of All SubDistricts in the given state
	 * @throws NotFoundException
	 */
	Subdistricts getListOfSubDistricts(ParentTypeSubdistrict parentType, int parentCode) throws NotFoundException;
	
	/**
	 * 
	 * @return              -     List of All Blocks of All States
	 * @throws NotFoundException
	 */
    Blocks getListOfBlocks() throws NotFoundException;
	
    /**
     * 
     * @param parentType    -     ParentType for which Blocks List is to be fetched possible values are:
	 *                            S = State
	 *                            D = District
	 * @param parentCode    -     ParentCode for which Blocks List is to be fetched
	 * @return              -     List of All Blocks in the given state
	 * @throws NotFoundException
     */
	Blocks getListOfBlocks(ParentTypeSubdistrict parentType, int parentCode) throws NotFoundException;

	/**
	 * 
	 * @return              -     List of All Villages of All States
	 * @throws NotFoundException
	 */
	Villages getListOfVillages() throws NotFoundException;
	
	/**
	 * 
	 * @param parentType    -     ParentType for which Village List is to be fetched, possible values are
	 *                            S = State
	 *                            D = District
	 *                            T = SubDistrcit
	 *                            B = Block
	 * @param parentCode    -     ParentCode for which Village List is to be fetched
	 * @return              -     List of All Villages in the given state
	 * @throws NotFoundException
	 */
	Villages getListOfVillages(ParentTypeVillage parentType, int parentCode) throws NotFoundException;
	
	/**
	 * 
	 * @param category      -     LocalBody Type for which LocalBody Details are being fetched
	 * @param lbCode        -     Code of Local Body
	 * @param languageType  -     Language in which data is to be returned, possible values are
	 *                            E = English
                                  L = Local
                                  A = All (Both) 
	 * @return              -     LocalBodyName depending on Language chosen
	 * @throws NotFoundException
	 */
	LocalBodyNames getLBName(Category category, int lbCode, LanguageType languageType) throws NotFoundException;
	
	/**
	 * 
	 * @param category      -     LocalBody Type for which LocalBody Details are being fetched
	 * @param languageType  -     Language in which data is to be returned possible values are
	 *                            E = English
                                  L = Local
                                  A = All (Both)
	 * @return              -     LocalBodyList depending on Language chosen
	 * @throws NotFoundException
	 */
	LocalBodyNames getLBList(Category category, LanguageType languageType) throws NotFoundException;
	
	/**
	 * 
	 * @param category      -     LocalBody Type for which LocalBody Details are being fetched
	 * @param stateCode     -     State for which LocalBody Details are being Fetched
	 * @param languageType  -     Language in which data is to be returned possible values are
	 *                            E = English
                                  L = Local
                                  A = All (Both)
	 * @return              -     LocalBodyList depending on Language chosen
	 * @throws NotFoundException
	 */
	LocalBodyNames getLBList(Category category, int stateCode, LanguageType languageType) throws NotFoundException;
	
	/**
	 * 
	 * @param parentType    -     ParentType for which LocalBody List is to be fetched possible values are:
	 *                            S = State
	 *                            D = District
	 *                            T = SubDistrcit
	 *                            B = Block
	 * @param parentCode    -     ParentCode for which LocalBody List is to be fetched
	 * @param languageType  -     Language in which data is to be returned possible values are
	 *                            E = English
                                  L = Local
                                  A = All (Both)
	 * @return              -     LocalBodyList depending on Language chosen
	 * @throws NotFoundException
	 */
	LocalBodyNames getParentWiseLBList(ParentTypeVillage parentType, int parentCode, LanguageType languageType) throws NotFoundException;
	
	/**
	 * 
	 * @param LRType        -     Land Region Type for which LocalBody List is to be fetched, possible values are
	 *                            S = STATE
	 *                            D = DISTRICT
	 *                            T = SUBDISTRICT
	 *                            B = BLOCK
	 *                            V = VILLAGE
	 * @param LRCode        -     Land Region Code for which LocalBody List is to be fetched
	 * @param category      -     LocalBody Type for which LocalBody List is to be fetched, possible values are
	 *                            U = URBAN
	 *                            R = RURAL
	 *                            T = TRADITIONAL
	 *                            A = ALL
	 * @param languageType  -     Language in which data is to be returned possible values are
	 *                            E = English
                                  L = Local
                                  A = All (Both)
	 * @return              -     LocalBodyList depending on Language chosen
	 * @throws NotFoundException
	 */
	LocalBodyNames getLandRegionWiseLBList(LandRegionTypes LRType, int LRCode, Category category, LanguageType languageType) throws NotFoundException;
	
	/**
	 * 
	 * @param category      -     LocalBody Type for which LocalBody Covered Area is to be fetched, possible values are
	 *                            U = URBAN
	 *                            R = RURAL
	 *                            T = TRADITIONAL
	 *                            A = ALL
	 * @param lbCode        -     LocalBody Code for which LocalBody covered area is to be fetched
	 * @param languageType  -     Language in which data is to be returned possible values are
	 *                            E = English
                                  L = Local
                                  A = All (Both)
	 * @return              -     LocalBodyList depending on Language chosen
	 * @throws NotFoundException
	 */
	LocalBodyCoveredArea getCoveredAreaofLB(Category category, int lbCode, LanguageType languageType) throws NotFoundException;
	
	/**
	 * 
	 * @param localGovId    -     Local GovernmentId for which Local Government List is to be fetched
	 * @param version       -     Version of Local Government from which onwards Local Government List is to be fetched
	 * @param languageType  -     Language in which data is to be returned possible values are
	 *                            E = English
                                  L = Local
                                  A = All (Both)
	 * @return              -     LocalBodyList depending on Language chosen
	 * @throws NotFoundException
	 */
	LocalGovList getNextVersion(int localGovId, int version, LanguageType languageType) throws NotFoundException;
}
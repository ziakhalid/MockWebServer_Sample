package co.zia.khalid.mockwebserver_sample.server

interface EndPointProvider {
    fun getEndPoint(): EndPoint
    fun getEndPointName(): String
    fun getCustomServerAddress(): String
    fun getWeatherEndpointUrl(): String
}
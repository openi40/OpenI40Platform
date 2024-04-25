export function getBaseUrl() {
  let host=document.location.hostname;
  let port=document.location.port;
  let protocol=document.location.protocol;
  if (port==="4200") {
    port="8080";
  }
  let openi40Base=protocol+"//"+host+":"+port+"/openi40";
  console.log("Setting basePath: "+openi40Base);
  return openi40Base;
}

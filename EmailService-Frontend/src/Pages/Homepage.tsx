// import Heading from "../Components/Homepage/Heading";

import AttachmentMail from "../Components/Homepage/AttachmentMail";
import Heading from "../Components/Homepage/Heading";
import SimpleMail from "../Components/Homepage/SimpleMail";
function Homepage(){
    return(
        <>
        <Heading/>
        <div className="flex flex-row justify-center space-x-8">
        <SimpleMail/><AttachmentMail/>
        </div>
        
        </>
    )
}

export default Homepage;
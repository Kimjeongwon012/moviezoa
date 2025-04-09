import React from "react";
import {Link} from 'react-router-dom';

function ErrorImage({src, className}) {
    return (
        <img src={src} alt="404 decorative illustration" className={className}/>
    );
}

export default ErrorImage;

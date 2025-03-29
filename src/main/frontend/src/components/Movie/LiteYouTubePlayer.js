import React, {useEffect} from "react";
import "lite-youtube-embed/src/lite-yt-embed.css";

function LiteYouTubePlayer({data}) {
    useEffect(() => {
        // 웹 컴포넌트를 등록해줘야 작동함
        if (!customElements.get("lite-youtube")) {
            import("lite-youtube-embed").catch(() => {
            });
        }
    }, []);

    return (
        <lite-youtube
            videoid={data.key}
            playlabel={data.name}
            style={{maxWidth: "100%", width: "560px", aspectRatio: "16/9"}}
        ></lite-youtube>
    );
}

export default LiteYouTubePlayer;

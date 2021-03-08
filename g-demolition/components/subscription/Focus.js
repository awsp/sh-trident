import {useEffect, useState} from "react";
import {Icon} from "@blueprintjs/core";

const Focus = ({focus}) => {
  const [feeds, setFeeds] = useState(null);

  const getFeeds = async (focusId) => {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/focus/${focusId}/feeds`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    return response.json();
  };

  useEffect(() => {
    getFeeds(focus.id).then(data => {
      setFeeds(data);
    });
    return () => {
      setFeeds(null);
    }
  }, [focus]);

  return <section>
    Focus: {focus.name}
    <ul>
      {feeds ?
        feeds.length > 0 ?
          feeds.map(feed => <li key={`feed-${feed.id}`}>
            {feed.title} <a target="_blank" href={feed.enclosure}><Icon icon="link" /></a>
          </li>) :
          <div>No feeds</div> :
        <div>Fetching feeds...</div>}
    </ul>
  </section>;
};

export default Focus;
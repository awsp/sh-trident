import {useEffect, useState} from "react";
import {useRouter} from "next/router";
import {Controls} from "../../components/controls";
import Focus from "../../components/subscription/focus";

const Subscription = () => {
  const router = useRouter();
  const {subId} = router.query;
  const [subscription, setSubscription] = useState(null);

  const getSubscription = async () => {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/subscription/${subId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    return response.json();
  };

  useEffect(() => {
    if (!router.isReady) return;
    getSubscription().then(subscriptionData => {
      setSubscription(subscriptionData);
    });
  }, [router.isReady]);

  return (
    <>
      <Controls/>
      <div>
        {subscription ? subscription.focuses.map(focus => (
          <Focus key={`focus-${focus.id}`} focus={focus}/>
        )) : <div>Loading</div>}
      </div>
    </>
  );
};

export default Subscription;
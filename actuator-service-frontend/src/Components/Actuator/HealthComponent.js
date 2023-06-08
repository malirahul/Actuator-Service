import { DescriptionList, Divider, LegacyCard, Page } from "@shopify/polaris";
import axios from "axios";
import React, { useEffect, useState } from "react";

export default function HealthComponent() {
  const [health, setHealth] = useState([]);

  useEffect(() => {
    loadHealth();
  }, []);

  const loadHealth = async () => {
    try {
      const result = await axios.get("http://localhost:9001/actuator/health", {
        headers: {
          "Content-Type": "application/json",
        },
      });
      var health = [];
      health = result.data;
      setHealth(health);
      console.log("data", health);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <Page title="Health">
        <LegacyCard>
          <DescriptionList
            items={[
              {
                term: "Health",

                description: health.status,
              },
              <Divider />,
              {
                term: "Disk Space",

                description: health.diskSpace,
              },
            ]}
          />
        </LegacyCard>
      </Page>
    </div>
  );
}

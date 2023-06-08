import React from "react";
import {
  HomeMinor,
  GlobeMajor,
  ProductsMinor,
  CustomersMinor,
  LogOutMinor,
} from "@shopify/polaris-icons";
import { Icon, Navigation } from "@shopify/polaris";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCheckCircle,
  faExclamationTriangle,
  faExclamationCircle,
  faServer,
} from "@fortawesome/free-solid-svg-icons";

import { useState } from "react";

// import { useNavigate } from "react-router-dom";

export const SideNavMarkup = () => {
  const [selectedNavbar, setselectedNavbar] = useState("");

  // const nav = useNavigate();

  return (
    <Navigation location="/">
      <Navigation.Section
        items={[
          {
            url: "/dashboard",
            label: "Home",
            icon: HomeMinor,
          },
          {
            url: "/health",
            excludePaths: ["#"],
            label: "Health",
            icon: GlobeMajor,
            color: "base",
          },
          {
            url: "/beans",
            //onClick: { navigateProduct },
            label: "Beans",
            icon: GlobeMajor,
            color: "base",
          },
          {
            url: "/caches",
            //onClick: { navigateProduct },
            label: "Cahes",
            icon: GlobeMajor,
            color: "base",
          },
          {
            url: "/loggers",
            //onClick: { navigateProduct },
            label: "Loggers",
            icon: GlobeMajor,
            color: "base",
          },
          {
            url: "/metrics",
            //onClick: { navigateProduct },
            label: "Metrics",
            icon: GlobeMajor,
            color: "base",
          },
          {
            url: "/env",
            //onClick: { navigateProduct },
            label: "Environment",
            icon: GlobeMajor,
            color: "base",
          },
          {
            url: "/profile",
            //onClick: { navigateProduct },
            label: "Profile",
            icon: CustomersMinor,
          },
        ]}
      />
    </Navigation>
  );
};

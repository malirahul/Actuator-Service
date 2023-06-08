import { TopBar, ActionList, Icon, Frame, Text } from "@shopify/polaris";
import { ArrowLeftMinor, QuestionMarkMajor } from "@shopify/polaris-icons";
import { useState, useCallback } from "react";

export const TopBarMarkup = () => {
  const [isUserMenuOpen, setIsUserMenuOpen] = useState(false);
  const [isSecondaryMenuOpen, setIsSecondaryMenuOpen] = useState(false);
  const [isSearchActive, setIsSearchActive] = useState(false);
  const [searchValue, setSearchValue] = useState("");

  const toggleIsUserMenuOpen = useCallback(
    () => setIsUserMenuOpen((isUserMenuOpen) => !isUserMenuOpen),
    []
  );

  const toggleIsSecondaryMenuOpen = useCallback(
    () => setIsSecondaryMenuOpen((isSecondaryMenuOpen) => !isSecondaryMenuOpen),
    []
  );

  const handleSearchResultsDismiss = useCallback(() => {
    setIsSearchActive(false);
    setSearchValue("");
  }, []);

  const handleSearchChange = useCallback((value) => {
    setSearchValue(value);
    setIsSearchActive(value.length > 0);
  }, []);

  const handleNavigationToggle = useCallback(() => {
    console.log("toggle navigation visibility");
  }, []);

  const logo = {
    width: 50,
    topBarSource:
      "https://1000logos.net/wp-content/uploads/2020/08/Shopify-Logo-500x313.png",
    url: "/home",
    accessibilityLabel: "Shopify Pixel",
  };

  const userMenuMarkup = (
    <TopBar.UserMenu
      actions={[
        {
          items: [
            {
              content: "Back to Shopify",
              icon: ArrowLeftMinor,
              url: "/dashboard",
            },
          ],
        },
        {
          items: [{ content: "Community forums" }],
        },
      ]}
      name="Actuator Services"
      initials="AS"
      open={isUserMenuOpen}
      onToggle={toggleIsUserMenuOpen}
    />
  );

  const searchResultsMarkup = (
    <ActionList
      items={[
        { content: "Custom", url: "/custom" },
        { content: "Beans", url: "/beans" },
        { content: "Caches", url: "/caches" },
        { content: "Health", url: "/health" },
        { content: "Conditions", url: "/conditions" },
        { content: "Loggers", url: "/loggers" },
        { content: "Configuration Properties", url: "/configprops" },
        { content: "Environment", url: "/env" },
        { content: "Heap Dump", url: "/heapdump" },
        { content: "Thread Dump", url: "/threaddump" },
        { content: "Prometheus", url: "/prometheus" },
        { content: "Metrics", url: "/metrics" },
        { content: "Scheduled Tasks", url: "/scheduledtasks" },
        { content: "Mapping", url: "/mapping" },

        { content: "Community forums" },
      ]}
    />
  );

  const searchFieldMarkup = (
    <TopBar.SearchField
      onChange={handleSearchChange}
      value={searchValue}
      placeholder="Search"
      showFocusBorder
    />
  );

  const secondaryMenuMarkup = (
    <TopBar.Menu
      activatorContent={
        <span>
          <Icon source={QuestionMarkMajor} />
          <Text as="span" visuallyHidden>
            Secondary menu
          </Text>
        </span>
      }
      open={isSecondaryMenuOpen}
      onOpen={toggleIsSecondaryMenuOpen}
      onClose={toggleIsSecondaryMenuOpen}
      actions={[
        {
          items: [{ content: "Community forums" }],
        },
      ]}
    />
  );

  return (
    <div>
      <TopBar
        showNavigationToggle
        userMenu={userMenuMarkup}
        secondaryMenu={secondaryMenuMarkup}
        searchResultsVisible={isSearchActive}
        searchField={searchFieldMarkup}
        searchResults={searchResultsMarkup}
        onSearchResultsDismiss={handleSearchResultsDismiss}
        onNavigationToggle={handleNavigationToggle}
        logo={logo}
      />
    </div>
  );
};

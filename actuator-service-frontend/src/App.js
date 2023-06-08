import logo from "./logo.svg";
import "./App.css";
import "./Components/assert/Metrics.css";
import "@shopify/polaris/build/esm/styles.css";
import { AppProvider, Frame } from "@shopify/polaris";
import {
  Routes,
  Route,
  BrowserRouter,
  Link as ReactRouterLink,
  useNavigate,
} from "react-router-dom";

import { TopBarMarkup } from "./navbar/TopBar";
import { SideNavMarkup } from "./navbar/SideBar";
import DashBoard from "./Components/DashBoard";
import Profile from "./Components/Profile";
import HealthComponent from "./Components/Actuator/HealthComponent";
import MetricsComponent from "./Components/Actuator/MetricsComponent";

function App() {
  return (
    <div className="App">
      <AppProvider linkComponent={Link}>
        <BrowserRouter>
          <Frame topBar={TopBarMarkup()} navigation={SideNavMarkup()}>
            <Routes>
              <Route path="" element={<DashBoard />} />
              <Route path="/dashboard" element={<DashBoard />} />
              <Route path="/profile" element={<Profile />} />

              <Route path="/health" element={<HealthComponent />} />
              <Route path="/metrics" element={<MetricsComponent />} />
            </Routes>
          </Frame>
        </BrowserRouter>
      </AppProvider>
    </div>
  );
}

const IS_EXTERNAL_LINK_REGEX = /^(?:[a-z][a-z\d+.-]*:|\/\/)/;

function Link({ children, url = "", external, ref, ...rest }) {
  if (external || IS_EXTERNAL_LINK_REGEX.test(url)) {
    rest.target = "_blank";

    rest.rel = "noopener noreferrer";

    return (
      <a href={url} {...rest}>
        {children}
      </a>
    );
  }

  return (
    <ReactRouterLink to={url} {...rest}>
      {children}
    </ReactRouterLink>
  );
}

export default App;
